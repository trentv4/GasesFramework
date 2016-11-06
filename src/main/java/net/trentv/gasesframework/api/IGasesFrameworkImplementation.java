package net.trentv.gasesframework.api;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IGasesFrameworkImplementation
{
	public GasType getGasType(BlockPos pos, World access);
	
	public int getGasLevel(BlockPos pos, World access);

	public boolean canPlaceGas(BlockPos pos, World access, @Nullable GasType currentBlock);

	public void setGasLevel(BlockPos pos, World access, GasType gas, int level);

	public int addGasLevel(BlockPos pos, World access, GasType gas, int level);

	public void removeGasLevel(BlockPos pos, World access, int level);
	
	public void tryIgniteGas(BlockPos pos, World access);
	
	public void addDelayedExplosion(BlockPos pos, World access, float power, boolean isFlaming, boolean isSmoking);
	
	public void registerGasType(GasType type);

	public GasType[] getGastypes();

	@Nullable
	public GasType getGasTypeByName(String name);
}
