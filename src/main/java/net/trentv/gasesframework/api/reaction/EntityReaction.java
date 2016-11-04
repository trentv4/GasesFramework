package net.trentv.gasesframework.api.reaction;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.api.GasType;

public interface EntityReaction
{
	public void react(Entity e, IBlockAccess access, GasType gas, BlockPos pos);
}
