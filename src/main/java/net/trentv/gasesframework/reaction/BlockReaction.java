package net.trentv.gasesframework.reaction;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class BlockReaction extends Reaction
{
	public abstract void react(IBlockAccess access, Block block, BlockPos pos);
}
