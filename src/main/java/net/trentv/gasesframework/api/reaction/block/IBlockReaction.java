package net.trentv.gasesframework.api.reaction.block;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IBlockReaction
{
	public void react(Block blockReactive, IBlockAccess access, BlockPos pos);
}
