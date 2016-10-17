package net.trentv.gasesframework.common;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.IGasesFrameworkImplementation;
import net.trentv.gasesframework.common.block.BlockGas;

public class GasesFrameworkImplementation implements IGasesFrameworkImplementation
{
	public int getGasLevel(BlockPos pos, IBlockAccess access)
	{
		IBlockState a = access.getBlockState(pos);
		if (a.getBlock() instanceof BlockGas)
		{
			return a.getValue(BlockGas.CAPACITY);
		}
		else
		{
			return 0;
		}
	}

	public boolean canPlaceGas(BlockPos pos, IBlockAccess access)
	{
		IBlockState a = access.getBlockState(pos);
		if (a == Blocks.AIR)
		{
			return true;
		}
		return false;
	}

	public boolean canPlaceGas(BlockPos pos, IBlockAccess access, BlockGas currentBlock)
	{
		IBlockState a = access.getBlockState(pos);
		if (a.getBlock() == Blocks.AIR | a.getBlock() == currentBlock)
		{
			return true;
		}
		return false;
	}

	public int addGasLevel(BlockPos pos, World access, BlockGas gas, int level)
	{
		if (level <= 16 & level > 0)
		{
			Block a = access.getBlockState(pos).getBlock();
			if (a == gas)
			{
				int oldLevel = access.getBlockState(pos).getValue(BlockGas.CAPACITY);
				int newLevel = oldLevel + level;
				if (newLevel >= 16)
				{
					access.setBlockState(pos, gas.getDefaultState().withProperty(BlockGas.CAPACITY, 16));
					return newLevel - 16;
				}
				else
				{
					access.setBlockState(pos, gas.getDefaultState().withProperty(BlockGas.CAPACITY, newLevel));
					return 0;
				}
			}
			else
			{
				access.setBlockState(pos, gas.getDefaultState().withProperty(BlockGas.CAPACITY, level));
				return 0;
			}
		}
		else if (level == 0)
		{
			access.setBlockToAir(pos);
		}
		else
		{
			GasesFramework.logger.error("Attempting to place an incorrect gas level of " + level);
		}
		return level;
	}
	
	public void removeGasLevel(BlockPos pos, World access, int level)
	{
		Block a = access.getBlockState(pos).getBlock();
		if(a instanceof BlockGas)
		{
			int newLevel = access.getBlockState(pos).getValue(BlockGas.CAPACITY) - 1;
			if(newLevel > 0)
			{
				access.setBlockState(pos, a.getDefaultState().withProperty(BlockGas.CAPACITY, newLevel));
			}
			else
			{
				access.setBlockToAir(pos);
			}
		}
	}

	public void setGasLevel(BlockPos pos, World access, BlockGas gas, int level)
	{
		if (level <= 16 & level > 0)
		{
			access.setBlockState(pos, gas.getDefaultState().withProperty(BlockGas.CAPACITY, level));
		}
		else if (level == 0)
		{
			access.setBlockToAir(pos);
		}
		else
		{
			GasesFramework.logger.error("Attempting to place an incorrect gas level of " + level);
		}
	}
}
