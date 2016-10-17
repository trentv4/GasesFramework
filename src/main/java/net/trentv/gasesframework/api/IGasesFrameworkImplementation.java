package net.trentv.gasesframework.api;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.trentv.gasesframework.common.BlockGas;

public interface IGasesFrameworkImplementation
{
	public int getGasLevel(BlockPos pos, IBlockAccess access);

	public boolean canPlaceGas(BlockPos pos, IBlockAccess access);

	public boolean canPlaceGas(BlockPos pos, IBlockAccess access, BlockGas currentBlock);

	public void setGasLevel(BlockPos pos, World access, BlockGas gas, int level);

	public int addGasLevel(BlockPos pos, World access, BlockGas gas, int level);
	
	public void removeGasLevel(BlockPos pos, World access, int level);
}
