package net.trentv.gasesframework.reaction;

import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.common.block.BlockGas;

public interface EntityReaction
{
	public void react(Entity e, IBlockAccess access, BlockGas gas);
}
