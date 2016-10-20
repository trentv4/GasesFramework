package net.trentv.gasesframework.sample;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.api.GasType;

public interface ISample
{
	GasType onSample(IBlockAccess access, BlockPos pos, GasType in, EnumFacing side);
}
