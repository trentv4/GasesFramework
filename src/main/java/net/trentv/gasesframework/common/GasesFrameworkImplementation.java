package net.trentv.gasesframework.common;

import javax.annotation.Nullable;

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
					setGasLevel(pos, access, gas, 16);
					return newLevel - 16;
				}
				else
				{
					setGasLevel(pos, access, gas, newLevel);
					return 0;
				}
			}
			else
			{
				setGasLevel(pos, access, gas, level);
				return 0;
			}
		}
		else if (level == 0)
		{
			setGasLevel(pos, access, gas, 0);
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
		if (a instanceof BlockGas)
		{
			int newLevel = access.getBlockState(pos).getValue(BlockGas.CAPACITY) - 1;
			if (newLevel > 0)
			{
				setGasLevel(pos, access, (BlockGas) a, newLevel);
			}
			else
			{
				setGasLevel(pos, access, (BlockGas) a, 0);
			}
		}
	}

	public void setGasLevel(BlockPos pos, World access, @Nullable BlockGas gas, int level)
	{
		if (level <= 16 & level > 0)
		{
			if (gas != null)
			{
				access.setBlockState(pos, gas.getDefaultState().withProperty(BlockGas.CAPACITY, level));
			}
			else
			{
				GasesFramework.logger.error("Attempting to place gas with null type");
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
	}
}
