package net.trentv.gasesframework.impl;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gfapi.GasType;
import net.trentv.gfapi.IManipulationAPI;

public class GFManipulationAPI implements IManipulationAPI
{
	@Override
	public GasType getGasType(BlockPos pos, World access)
	{
		return null;
	}

	@Override
	public int getGasLevel(BlockPos pos, World access)
	{
		return 0;
	}

	@Override
	public boolean canPlaceGas(BlockPos pos, World access, GasType currentGas)
	{
		return false;
	}

	@Override
	public void setGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		
	}

	@Override
	public void removeGasLevel(BlockPos pos, World access, int level)
	{
		
	}

	@Override
	public int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		return 0;
	}
}
