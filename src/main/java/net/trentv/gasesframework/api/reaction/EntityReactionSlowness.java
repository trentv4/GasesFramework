package net.trentv.gasesframework.api.reaction;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.capability.GasEffectsProvider;
import net.trentv.gasesframework.api.capability.IGasEffects;

public class EntityReactionSlowness implements EntityReaction
{
	public float slownessRate;

	public EntityReactionSlowness(float slownessRate)
	{
		this.slownessRate = slownessRate;
	}

	@Override
	public void react(Entity e, IBlockAccess access, GasType gas, BlockPos pos)
	{
		if (e.hasCapability(GasEffectsProvider.GAS_EFFECTS, null))
		{
			IGasEffects q = e.getCapability(GasEffectsProvider.GAS_EFFECTS, null);
			if(new BlockPos(e.getPositionEyes(0)).toLong() == pos.toLong())
			{
				if(!access.isAirBlock(new BlockPos(e.getPositionEyes(0))))
				{
					if(q.getSlowness() < 100 - slownessRate)
					{
						q.setSlowness(q.getSlowness() + slownessRate);
					}
				}
			}
		}		
	}
}
