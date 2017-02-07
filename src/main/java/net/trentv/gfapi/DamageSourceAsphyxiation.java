package net.trentv.gfapi;

import net.minecraft.util.DamageSource;

public class DamageSourceAsphyxiation extends DamageSource
{
	public DamageSourceAsphyxiation(String damageTypeIn)
	{
		super(damageTypeIn);
		this.setDamageBypassesArmor();
	}
}
