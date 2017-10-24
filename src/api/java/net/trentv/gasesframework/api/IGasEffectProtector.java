package net.trentv.gasesframework.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.trentv.gasesframework.api.reaction.entity.IEntityReaction;

public interface IGasEffectProtector
{
	public boolean apply(EntityLivingBase entity, IEntityReaction reaction, GasType type, ItemStack itemstack);
}
