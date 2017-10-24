package net.trentv.gasesframework.impl;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.GasesFrameworkAPI;
import net.trentv.gasesframework.common.block.BlockGas;

public class ImplManipulationAPI implements GFManipulationAPI.IManipulationAPI
{
	public ImplManipulationAPI()
	{
		GFManipulationAPI.instance = this;
	}

	@Override
	public GasType getGasType(BlockPos pos, World access)
	{
		IBlockState a = access.getBlockState(pos);
		if (a.getBlock() instanceof BlockGas)
		{
			return ((BlockGas) a.getBlock()).gasType;
		}
		else
		{
			return GasesFrameworkAPI.AIR;
		}
	}

	@Override
	public int getGasLevel(BlockPos pos, World access)
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

	@Override
	public boolean canPlaceGas(BlockPos pos, World access, GasType currentGas)
	{
		if (currentGas == GasesFrameworkAPI.AIR | pos.getY() < 0)
		{
			return false;
		}
		IBlockState a = access.getBlockState(pos);
		if (currentGas == null || currentGas.block == null)
			return false;
		if (a.getBlock() == Blocks.AIR | a.getBlock() == currentGas.block)
		{
			return true;
		}
		return false;
	}

	@Override
	public void setGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if (gas == GasesFrameworkAPI.AIR)
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

	@Override
	public void removeGasLevel(BlockPos pos, World access, int level)
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

	@Override
	public int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if (gas == GasesFrameworkAPI.AIR)
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

	@Override
	public void addGasLevelOverflow(BlockPos pos, World world, GasType gas, int level)
	{
		if (gas == GasesFrameworkAPI.AIR)
		{
			return;
		}
		else if (level > 0)
		{
			if (canPlaceGas(pos, world, gas))
			{
				if (placeRecursive(pos, world, gas, level) == 0)
				{
					System.out.println("Finished placing");
				}
			}
		}
		else
		{
			GasesFramework.logger.error("Attempting to place an incorrect gas level of " + level);
		}
	}

	private int placeRecursive(BlockPos pos, World world, GasType gas, int originalLevel)
	{
		if (originalLevel == 0)
			return 0;
		if (!canPlaceGas(pos, world, gas))
			return originalLevel;

		int level = originalLevel;

		if (originalLevel > 16)
		{
			level -= (16 + addGasLevel(pos, world, gas, 16));
		}
		else if (originalLevel < 16 & originalLevel > 0)
		{
			level -= (16 + addGasLevel(pos, world, gas, level));
		}

		EnumFacing[] faces = getValidFacings(pos, world, gas);
		int[] distributedLevels = new int[faces.length];
		int g = 0;
		for (int i = level; i > 0; i--)
		{
			distributedLevels[g]++;
			g++;
			if (g >= distributedLevels.length)
			{
				g = 0;
			}
		}
		int remaining = 0;
		for (int i = 0; i < faces.length; i++)
		{
			if (distributedLevels[i] == 0)
				return 0;
			remaining += placeRecursive(pos.offset(faces[i]), world, gas, distributedLevels[i]);
		}

		return remaining;
	}

	private EnumFacing[] getValidFacings(BlockPos pos, World world, GasType gas)
	{
		ArrayList<EnumFacing> validList = new ArrayList<EnumFacing>();

		for (EnumFacing facing : EnumFacing.values())
		{
			if (canPlaceGas(pos, world, gas))
			{
				validList.add(facing);
			}
		}

		return validList.toArray(new EnumFacing[validList.size()]);
	}
}
