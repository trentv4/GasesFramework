package net.trentv.gasesframework.api.reaction.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.capability.IGasEffects;

public class EntityReactionSuffocation implements IEntityReaction
{
	@CapabilityInject(IGasEffects.class)
	public static Capability<IGasEffects> GAS_EFFECTS;

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
		if (e.hasCapability(GAS_EFFECTS, null))
		{
			IGasEffects q = e.getCapability(GAS_EFFECTS, null);
			if (new BlockPos(e.getPositionEyes(0)).toLong() == pos.toLong())
			{
				if (!access.isAirBlock(new BlockPos(e.getPositionEyes(0))))
				{
					if (q.getSuffocation() < 200)
					{
						q.setSuffocation(q.getSuffocation() + suffocationRate);
					}
					else
					{
						e.attackEntityFrom(GasesFramework.damageSourceAsphyxiation, damage);
					}
				}
			}
		}
	}
}
