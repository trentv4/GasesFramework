package net.trentv.gasesframework.api;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IGasesFrameworkImplementation
{
	public int getGasLevel(BlockPos pos, IBlockAccess access);

	public boolean canPlaceGas(BlockPos pos, IBlockAccess access);

	public boolean canPlaceGas(BlockPos pos, IBlockAccess access, GasType currentBlock);

	public void setGasLevel(BlockPos pos, World access, GasType gas, int level);

	public int addGasLevel(BlockPos pos, World access, GasType gas, int level);

	public void removeGasLevel(BlockPos pos, World access, int level);
}
