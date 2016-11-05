package net.trentv.gasesframework.common.block;

import static net.trentv.gasesframework.GasesFramework.implementation;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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
import net.trentv.gasesframework.api.reaction.EntityReaction;
import net.trentv.gasesframework.api.sample.ISample;
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
		
		gasType.preTick(world, state, currentPosition);
		
		if(gasType.dissipationRate > 0)
		{
			if(rand.nextInt(16) < gasType.dissipationRate)
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
				if (implementation.canPlaceGas(flowToBlock, world, this.gasType) & thisValue > gasType.cohesion)
				{
					int flowValue = implementation.getGasLevel(flowToBlock, world);
					if (flowValue + 1 < thisValue)
					{
						implementation.addGasLevel(flowToBlock, world, this.gasType, 1);
						implementation.setGasLevel(currentPosition, world, this.gasType, implementation.getGasLevel(currentPosition, world) - 1);
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
				int remaining = implementation.addGasLevel(nextPosition, world, this.gasType, thisValue);
				if (state.getValue(CAPACITY) != remaining)
				{
					implementation.setGasLevel(currentPosition, world, this.gasType, remaining);
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
						if (implementation.canPlaceGas(flowToBlock, world, this.gasType))
						{
							int flowValue = implementation.getGasLevel(flowToBlock, world);
							if (flowValue + 1 < thisValue)
							{
								implementation.addGasLevel(flowToBlock, world, this.gasType, 1);
								implementation.setGasLevel(nextPosition, world, this.gasType, implementation.getGasLevel(nextPosition, world) - 1);
							}
						}
					}
				}
			}
		}

		gasType.postTick(world, state, currentPosition);
		world.scheduleBlockUpdate(currentPosition, this, tickRate, 1);
	}

	public BlockPos scanForOpenBlock(World world, BlockGas gas, BlockPos pos, EnumFacing direction)
	{
		if (implementation.canPlaceGas(pos.offset(direction), world, gas.gasType))
		{
			BlockPos p = pos.offset(direction);
			return pos.offset(direction);
		}

		EnumFacing newDir = EnumFacing.NORTH;
		for (int i = 0; i < 4; i++)
		{
			newDir = newDir.rotateY();
			if (implementation.canPlaceGas(pos.offset(newDir), world, gas.gasType) & implementation.canPlaceGas(pos.offset(newDir).offset(direction), world, gas.gasType))
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
		if(gasType.combustability.explosionPower > 0)
		{
			GasesFramework.implementation.addDelayedExplosion(pos, access, gasType.combustability.explosionPower, true, true);
			if(gasType.combustability.fireSpreadRate > 0)
			{
				GasesFramework.implementation.setGasLevel(pos, access, GasesFrameworkObjects.gasTypeFire, access.getBlockState(pos).getValue(CAPACITY));
			}
		}
	}
	
	public boolean isEntityHeadWithinBlock(Entity e, IBlockAccess access)
	{
		return access.getBlockState(new BlockPos(e.getPositionEyes(0))).getBlock() instanceof BlockGas;
	}
	
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		EntityReaction[] s = gasType.getEntityReactions();
		for (EntityReaction a : s)
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
