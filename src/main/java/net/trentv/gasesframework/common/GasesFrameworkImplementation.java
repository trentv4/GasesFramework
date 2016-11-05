package net.trentv.gasesframework.common;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.GFAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.IGasesFrameworkImplementation;
import net.trentv.gasesframework.common.block.BlockGas;
import net.trentv.gasesframework.common.entity.EntityDelayedExplosion;

public class GasesFrameworkImplementation implements IGasesFrameworkImplementation
{
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

	public boolean canPlaceGas(BlockPos pos, World access, GasType currentGasType)
	{
		if(currentGasType == GFAPI.gasTypeAir)
		{
			return false;
		}
		IBlockState a = access.getBlockState(pos);
		if (a.getBlock() == Blocks.AIR | a.getBlock() == currentGasType.block)
		{
			return true;
		}
		return false;
	}

	public int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if(gas == GFAPI.gasTypeAir)
		{
			return 0;
		}
		if (level <= 16 & level > 0)
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

	public void setGasLevel(BlockPos pos, World access, @Nullable GasType gas, int level)
	{
		if(gas == GFAPI.gasTypeAir)
		{
			access.setBlockToAir(pos);
		}
		if (level <= 16 & level > 0)
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
		else if (level == 0)
		{
			access.setBlockToAir(pos);
		}
		else
		{
			GasesFramework.logger.error("Attempting to place an incorrect gas level of " + level);
		}
	}

	@Override
	public void tryIgniteGas(BlockPos pos, World access)
	{
		if(access.getBlockState(pos).getBlock() instanceof BlockGas)
		{
			BlockGas a = (BlockGas) access.getBlockState(pos).getBlock();
			a.ignite(pos, access);
		}
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
			return GFAPI.gasTypeAir;
		}
	}

	@Override
	public void addDelayedExplosion(BlockPos pos, World access, float power, boolean isFlaming, boolean isSmoking)
	{
		EntityDelayedExplosion exploder = new EntityDelayedExplosion(access, 5, power, isFlaming, isSmoking);
		exploder.setPosition(pos.getX(), pos.getY(), pos.getZ());
		access.spawnEntityInWorld(exploder);
	}
}
