package net.trentv.gasesframework.reaction;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.capability.GasEffectsProvider;
import net.trentv.gasesframework.capability.IGasEffects;
import net.trentv.gasesframework.common.block.BlockGas;

public class EntityReactionBlindness implements EntityReaction
{
	public float blindnessRate;

	public EntityReactionBlindness(float blindnessRate)
	{
		this.blindnessRate = blindnessRate;
	}

	@Override
	public void react(Entity e, IBlockAccess access, BlockGas gas)
	{
		if (e.hasCapability(GasEffectsProvider.GAS_EFFECTS, null))
		{
			IGasEffects q = e.getCapability(GasEffectsProvider.GAS_EFFECTS, null);
			if(gas.isEntityHeadWithinBlock(e, access))
			{
				if(q.getBlindness() < 250)
				{
					q.setBlindness(q.getBlindness() + 2);
				}
			}
		}
	}
}
