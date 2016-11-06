package net.trentv.gasesframework.api.dummy;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.IGasesFrameworkImplementation;

public class GasesFrameworkDummyImplementation implements IGasesFrameworkImplementation
{
	@Override
	public int getGasLevel(BlockPos pos, World access)
	{
		return 0;
	}

	@Override
	public boolean canPlaceGas(BlockPos pos, World access, @Nullable GasType currentBlock)
	{
		return false;
	}

	@Override
	public void setGasLevel(BlockPos pos, World access, GasType gas, int level)
	{

	}

	@Override
	public int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		return 0;
	}

	@Override
	public void removeGasLevel(BlockPos pos, World access, int level)
	{

	}

	@Override
	public void tryIgniteGas(BlockPos pos, World access)
	{
		
	}

	@Override
	public GasType getGasType(BlockPos pos, World access)
	{
		return null;
	}

	@Override
	public void addDelayedExplosion(BlockPos pos, World access, float power, boolean isFlaming, boolean isSmoking)
	{
		
	}

	@Override
	public void registerGasType(GasType type)
	{
		
	}

	@Override
	public GasType[] getGastypes()
	{
		return null;
	}

	@Override
	public GasType getGasTypeByName(String name)
	{
		return null;
	}
}
