package net.trentv.gasesframework.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.MaterialGas;
import net.trentv.gasesframework.api.reaction.entity.IEntityReaction;
import net.trentv.gasesframework.api.sample.ISample;
import net.trentv.gasesframework.common.entity.EntityDelayedExplosion;
import net.trentv.gasesframework.impl.GFManipulationAPI;
import net.trentv.gasesframework.impl.GFRegistrationAPI;
import net.trentv.gasesframework.init.GasesFrameworkObjects;

public class BlockGas extends Block implements ISample
{
	public GasType gasType;

	private static final int tickRate = 10;
	public static final PropertyInteger CAPACITY = PropertyInteger.create("capacity", 1, 16);

	public BlockGas(GasType type)
	{
		super(MaterialGas.INSTANCE);
		this.gasType = type;
		disableStats();
		setTickRandomly(true);
		setHardness(0.0f);
		setLightOpacity(type.opacity);
		setCreativeTab(type.creativeTab);
		setResistance(0);
		setUnlocalizedName("gas_" + type.name);
		this.setDefaultState(blockState.getBaseState().withProperty(CAPACITY, 16));
	}

	// Block & block state

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleBlockUpdate(pos, this, tickRate, 1);
	}

	@Override
	public void updateTick(World world, BlockPos currentPosition, IBlockState state, Random rand)
	{
		// Considerations:
		//
		// Optimize to only run when the block next to it updates (after coming
		// to rest)
		// Look into MutableBlockPos to reduce GC overhead

		if (!gasType.tick(world, state, currentPosition))
		{
			return;
		}
		
		for (int i = 0; i < EnumFacing.VALUES.length; i++)
		{
			BlockPos scanBlock = currentPosition.offset(EnumFacing.VALUES[i]);
			if(GFRegistrationAPI.isIgnitionBlock(world.getBlockState(scanBlock).getBlock()))
			{
				ignite(currentPosition, world);
				return;
			}
		}

		if (gasType.dissipationRate > 0)
		{
			if (rand.nextInt(16) < gasType.dissipationRate)
			{
				state = state.withProperty(CAPACITY, state.getValue(CAPACITY) - 1);
			}
		}

		// If density is 0, we're going to be spreading out in a cloud
		if (gasType.density == 0)
		{
			EnumFacing newDir = EnumFacing.SOUTH;
			// Iterate through all directions (up/down/left/right/front/back)
			for (int i = 0; i < EnumFacing.VALUES.length; i++)
			{
				newDir = EnumFacing.VALUES[i];
				BlockPos flowToBlock = currentPosition.offset(newDir);
				int thisValue = state.getValue(CAPACITY);
				// Checks if it can flow into the block AND the current gas
				// capacity is over the cohesion level
				if (GFManipulationAPI.canPlaceGas(flowToBlock, world, this.gasType) & thisValue > gasType.cohesion)
				{
					int flowValue = GFManipulationAPI.getGasLevel(flowToBlock, world);
					if (flowValue + 1 < thisValue)
					{
						GFManipulationAPI.addGasLevel(flowToBlock, world, this.gasType, 1);
						GFManipulationAPI.setGasLevel(currentPosition, world, this.gasType, GFManipulationAPI.getGasLevel(currentPosition, world) - 1);
					}
				}
			}
		}
		// We're going to be flowing either up or down here because density != 0
		else
		{
			// Decide which direction we're going
			EnumFacing direction = EnumFacing.DOWN;
			if (gasType.density > 0)
				direction = EnumFacing.UP;

			BlockPos nextPosition = scanForOpenBlock(world, this, currentPosition, direction);
			int thisValue = state.getValue(CAPACITY);
			if (!nextPosition.equals(currentPosition)) // In this case, we'll be
														// flowing somewhere
														// above or below.
			{
				int remaining = GFManipulationAPI.addGasLevel(nextPosition, world, this.gasType, thisValue);
				if (state.getValue(CAPACITY) != remaining)
				{
					GFManipulationAPI.setGasLevel(currentPosition, world, this.gasType, remaining);
				}
			}
			else // Can't flow above or below, so time to spill out on the
					// ground
			{
				if (thisValue > 1)
				{
					EnumFacing newDir = EnumFacing.SOUTH;
					for (int i = 0; i < 4; i++)
					{
						newDir = newDir.rotateY();
						BlockPos flowToBlock = nextPosition.offset(newDir);
						if (GFManipulationAPI.canPlaceGas(flowToBlock, world, this.gasType))
						{
							int flowValue = GFManipulationAPI.getGasLevel(flowToBlock, world);
							if (flowValue + 1 < thisValue)
							{
								GFManipulationAPI.addGasLevel(flowToBlock, world, this.gasType, 1);
								GFManipulationAPI.setGasLevel(nextPosition, world, this.gasType, GFManipulationAPI.getGasLevel(nextPosition, world) - 1);
							}
						}
					}
				}
			}
		}

		world.scheduleBlockUpdate(currentPosition, this, tickRate, 1);
	}

	public BlockPos scanForOpenBlock(World world, BlockGas gas, BlockPos pos, EnumFacing direction)
	{
		if (GFManipulationAPI.canPlaceGas(pos.offset(direction), world, gas.gasType))
		{
			return pos.offset(direction);
		}

		EnumFacing newDir = EnumFacing.NORTH;
		for (int i = 0; i < 4; i++)
		{
			newDir = newDir.rotateY();
			if (GFManipulationAPI.canPlaceGas(pos.offset(newDir), world, gas.gasType) & GFManipulationAPI.canPlaceGas(pos.offset(newDir).offset(direction), world, gas.gasType))
			{
				return pos.offset(newDir);
			}
		}

		return pos;
	}

	@Override
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, CAPACITY);
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(CAPACITY, 16 - meta);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 16 - state.getValue(CAPACITY);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
	{
		ignite(pos, world);
	}
	
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 0;
    }

	// Client Side

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		IBlockState nState = blockAccess.getBlockState(pos.offset(side));
		if (nState.getBlock() == this)
		{
			if (blockState.getValue(CAPACITY) > nState.getValue(CAPACITY))
			{
				return true;
			}
			return true;
		}
		return true;
	}

	// Gases relevant

	public void ignite(BlockPos pos, World access)
	{
		if (!gasType.ignite(access, access.getBlockState(pos), pos))
		{
			return;
		}
		if (gasType.combustability.explosionPower > 0)
		{
			EntityDelayedExplosion exploder = new EntityDelayedExplosion(access, 5, (16 / access.getBlockState(pos).getValue(CAPACITY)) * gasType.combustability.explosionPower, true, true);
			exploder.setPosition(pos.getX(), pos.getY(), pos.getZ());
			access.spawnEntityInWorld(exploder);
			GFManipulationAPI.setGasLevel(pos, access, GasesFramework.AIR, 16);
		}
		if (gasType.combustability.fireSpreadRate > 0)
		{
			GFManipulationAPI.setGasLevel(pos, access, GasesFrameworkObjects.FIRE, access.getBlockState(pos).getValue(CAPACITY));
		}
	}

	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = new ArrayList<ItemStack>();
		return ret;
	}

	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		IEntityReaction[] s = gasType.getEntityReactions();
		for (IEntityReaction a : s)
		{
			a.react(entity, world, this.gasType, pos);
		}
	}

	@Override
	public GasType onSample(IBlockAccess access, BlockPos pos, GasType in, EnumFacing side)
	{
		return this.gasType;
	}
}
