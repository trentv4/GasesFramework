package net.trentv.gasesframework.api.dummy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.trentv.gasesframework.api.IGasesFrameworkImplementation;
import net.trentv.gasesframework.common.BlockGas;

public class GasesFrameworkDummyImplementation implements IGasesFrameworkImplementation
{
	@Override
	public int getGasLevel(BlockPos pos, IBlockAccess access)
	{
		return 0;
	}

	@Override
	public boolean canPlaceGas(BlockPos pos, IBlockAccess access)
	{
		return false;
	}

	@Override
	public boolean canPlaceGas(BlockPos pos, IBlockAccess access, BlockGas currentBlock)
	{
		return false;
	}

	@Override
	public void setGasLevel(BlockPos pos, World access, BlockGas gas, int level)
	{

	}

	@Override
	public int addGasLevel(BlockPos pos, World access, BlockGas gas, int level)
	{
		return 0;
	}

	@Override
	public void removeGasLevel(BlockPos pos, World access, int level)
	{
		
	}
}
