package net.trentv.gfapi.sample;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gfapi.GasType;

public interface ISample
{
	GasType onSample(IBlockAccess access, BlockPos pos, GasType in, EnumFacing side);
}
