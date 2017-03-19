package net.trentv.gasesframework.api;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.common.block.BlockGas;

public class GFManipulationAPI
{
	public static GasType getGasType(BlockPos pos, World access)
	{
		IBlockState a = access.getBlockState(pos);
		if (a.getBlock() instanceof BlockGas)
		{
			return ((BlockGas) a.getBlock()).gasType;
		}
		else
		{
			return GasesFramework.AIR;
		}
	}

	public static int getGasLevel(BlockPos pos, World access)
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

	public static boolean canPlaceGas(BlockPos pos, World access, GasType currentGas)
	{
		if (currentGas == GasesFramework.AIR)
		{
			return false;
		}
		IBlockState a = access.getBlockState(pos);
		if (a.getBlock() == Blocks.AIR | a.getBlock() == currentGas.block)
		{
			return true;
		}
		return false;
	}

	public static void setGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if (gas == GasesFramework.AIR)
		{
			access.setBlockToAir(pos);
		}
		else if (level <= 16 & level > 0)
		{
			if (gas != null)
			{
				access.setBlockState(pos, gas.block.getDefaultState().withProperty(BlockGas.CAPACITY, level));
			}
			else
			{
				GasesFramework.logger.error("Attempting to place gas with null type");
			}
		}
		else
		{
			access.setBlockToAir(pos);
		}
	}

	public static void removeGasLevel(BlockPos pos, World access, int level)
	{
		Block a = access.getBlockState(pos).getBlock();
		if (a instanceof BlockGas)
		{
			int newLevel = access.getBlockState(pos).getValue(BlockGas.CAPACITY) - 1;
			BlockGas b = (BlockGas) a;
			if (newLevel > 0)
			{
				setGasLevel(pos, access, b.gasType, newLevel);
			}
			else
			{
				setGasLevel(pos, access, b.gasType, 0);
			}
		}
	}

	public static int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if (gas == GasesFramework.AIR)
		{
			return 0;
		}
		else if (level <= 16 & level > 0)
		{
			Block a = access.getBlockState(pos).getBlock();
			if (a instanceof BlockGas && ((BlockGas) a).gasType == gas)
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
}
