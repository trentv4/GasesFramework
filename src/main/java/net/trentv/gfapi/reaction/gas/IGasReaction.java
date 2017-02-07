package net.trentv.gfapi.reaction.gas;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gfapi.GasType;

public interface IGasReaction
{
	public void react(GasType gasReactive, IBlockAccess access, BlockPos pos);
}
