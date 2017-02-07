package net.trentv.gfapi.reaction.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gfapi.GFAPI;
import net.trentv.gfapi.GasType;
import net.trentv.gfapi.capability.GasEffectsProvider;
import net.trentv.gfapi.capability.IGasEffects;

public class EntityReactionSuffocation implements IEntityReaction
{
	public int damage;
	public int suffocationRate;

	public EntityReactionSuffocation(int suffocationRate, int damage)
	{
		this.suffocationRate = suffocationRate;
		this.damage = damage;
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
					if(q.getSuffocation() > 0 + suffocationRate)
					{
						q.setSuffocation(q.getSuffocation() - suffocationRate);
					}
					else
					{
						e.attackEntityFrom(GFAPI.damageSourceAsphyxiation, damage);
					}
				}
			}
		}		
	}
}
