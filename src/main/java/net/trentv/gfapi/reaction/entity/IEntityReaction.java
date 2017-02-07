package net.trentv.gfapi.reaction.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gfapi.GasType;

public interface IEntityReaction
{
	public void react(Entity e, IBlockAccess access, GasType gas, BlockPos pos);
}
