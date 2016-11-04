package net.trentv.gasesframework.reaction;

import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.capabilities.Capability;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.capability.GasEffectsProvider;
import net.trentv.gasesframework.capability.IGasEffects;
import net.trentv.gasesframework.common.block.BlockGas;

public class EntityReactionSuffocation implements EntityReaction
{
	public int damage;
	public int suffocationRate;

	public EntityReactionSuffocation(int suffocationRate, int damage)
	{
		this.suffocationRate = suffocationRate;
		this.damage = damage;
	}

	@Override
	public void react(Entity e, IBlockAccess access, BlockGas gas)
	{
		if (e.hasCapability(GasEffectsProvider.GAS_EFFECTS, null))
		{
			IGasEffects q = e.getCapability(GasEffectsProvider.GAS_EFFECTS, null);
			if(q.getSuffocation() > 0)
			{
				q.setSuffocation(q.getSuffocation() - suffocationRate);
			}
			else
			{
				e.attackEntityFrom(GasesFramework.damageSourceAsphyxiation, damage);
			}
		}
	}
}
