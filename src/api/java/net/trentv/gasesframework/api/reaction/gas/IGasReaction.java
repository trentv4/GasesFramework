package net.trentv.gasesframework.api.reaction.gas;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.api.GasType;

public interface IGasReaction
{
	public void react(GasType gasReactive, IBlockAccess access, BlockPos pos);
}
