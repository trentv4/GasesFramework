package net.trentv.gasesframework.api.reaction;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.capability.GasEffectsProvider;
import net.trentv.gasesframework.api.capability.IGasEffects;

public class EntityReactionBlindness implements EntityReaction
{
	public float blindnessRate;

	public EntityReactionBlindness(float blindnessRate)
	{
		this.blindnessRate = blindnessRate;
	}

	@Override
	public void react(Entity e, IBlockAccess access, GasType gas, BlockPos pos)
	{
		if (e.hasCapability(GasEffectsProvider.GAS_EFFECTS, null))
		{
			IGasEffects q = e.getCapability(GasEffectsProvider.GAS_EFFECTS, null);
			if(!access.isAirBlock(new BlockPos(e.getPositionEyes(0))))
			{
				if(q.getBlindness() < 250 - blindnessRate)
				{
					q.setBlindness(q.getBlindness() + blindnessRate);
				}
			}
		}
	}
}
