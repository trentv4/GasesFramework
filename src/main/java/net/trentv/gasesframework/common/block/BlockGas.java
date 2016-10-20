package net.trentv.gasesframework.common.block;

import static net.trentv.gasesframework.GasesFramework.implementation;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.MaterialGas;
import net.trentv.gasesframework.reaction.EntityReaction;
import net.trentv.gasesframework.sample.ISample;

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
	public void updateTick(World worldIn, BlockPos position, IBlockState state, Random rand)
	{ 
		//Considerations:
		//
		//    Optimize to only run when the block next to it updates (after coming to rest)
		//    Look into MutableBlockPos to reduce GC overhead
		if (gasType.density == 0)
		{
			EnumFacing newDir = EnumFacing.SOUTH;
			for(int i = 0; i < EnumFacing.VALUES.length; i++)
			{
				newDir = EnumFacing.VALUES[i];
				BlockPos flowToBlock = position.offset(newDir);
				int thisValue = state.getValue(CAPACITY);
				if(implementation.canPlaceGas(flowToBlock, worldIn, this) & thisValue > gasType.cohesion)
				{
					int flowValue = implementation.getGasLevel(flowToBlock, worldIn);
					if(flowValue + 1 < thisValue)
					{
						implementation.addGasLevel(flowToBlock, worldIn, this, 1);
						implementation.setGasLevel(position, worldIn, this, implementation.getGasLevel(position, worldIn) - 1);
					}
				}
			}
		}
		else
		{
			EnumFacing direction = EnumFacing.DOWN;
			if(gasType.density > 0) direction = EnumFacing.UP;
			BlockPos pos = scan(worldIn, this, position, direction);
			int thisValue = state.getValue(CAPACITY);
			if(!pos.equals(position)) //In this case, we'll be flowing somewhere above or below.
			{
				int remaining = implementation.addGasLevel(pos, worldIn, this, thisValue);
				if (state.getValue(CAPACITY) != remaining)
				{
					implementation.setGasLevel(position, worldIn, this, remaining);
				}
			}
			else //Can't flow above or below, so time to spill out on the ground
			{
				if(thisValue > 1)
				{
					EnumFacing newDir = EnumFacing.SOUTH;
					for(int i = 0; i < 4; i++)
					{
						newDir = newDir.rotateY();
						BlockPos flowToBlock = pos.offset(newDir);
						if(implementation.canPlaceGas(flowToBlock, worldIn, this))
						{
							int flowValue = implementation.getGasLevel(flowToBlock, worldIn);
							if(flowValue + 1 < thisValue)
							{
								implementation.addGasLevel(flowToBlock, worldIn, this, 1);
								implementation.setGasLevel(pos, worldIn, this, implementation.getGasLevel(pos, worldIn) - 1);
							}
						}
					}
				}
			}
		}

		worldIn.scheduleBlockUpdate(position, this, tickRate, 1);
	}

	public BlockPos scan(World world, BlockGas gas, BlockPos pos, EnumFacing direction)
	{
		if (implementation.canPlaceGas(pos.offset(direction), world, gas))
		{
			BlockPos p = pos.offset(direction);
			return pos.offset(direction);
		}
		
		EnumFacing newDir = EnumFacing.NORTH;
		for(int i = 0; i < 4; i++)
		{
			newDir = newDir.rotateY();
			if(implementation.canPlaceGas(pos.offset(newDir), world, gas) & 
			   implementation.canPlaceGas(pos.offset(newDir).offset(direction), world, gas))
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
    
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
    	EntityReaction[] reactions = gasType.getEntityReactions();
    	for(EntityReaction reaction : reactions)
    	{
    		reaction.react(entity);
    	}
    }

	@Override
	public GasType onSample(IBlockAccess access, BlockPos pos, GasType in, EnumFacing side)
	{
		return this.gasType;
	}
}
