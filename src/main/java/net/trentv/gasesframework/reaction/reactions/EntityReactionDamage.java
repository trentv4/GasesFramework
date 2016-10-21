package net.trentv.gasesframework.reaction.reactions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.trentv.gasesframework.reaction.EntityReaction;

public class EntityReactionDamage extends EntityReaction
{
	public final DamageSource source;
	public final float damage;
	
	
	public EntityReactionDamage(int maxDelay, DamageSource source, float damage)
	{
		this.source = source;
		this.damage = damage;
	}
	
	@Override
	public void react(Entity e)
	{
		if(e instanceof EntityLivingBase)
		{
			e.attackEntityFrom(source, damage);
		}
	}

	@Override
	public int getCurrentDelay()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaximumDelay()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
