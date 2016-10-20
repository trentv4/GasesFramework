package net.trentv.gasesframework.reaction;

import net.minecraft.util.math.BlockPos;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.common.block.BlockGas;

public abstract class GasReaction extends Reaction
{
	public abstract void react(GasType e, BlockPos pos, BlockGas gas);
}
