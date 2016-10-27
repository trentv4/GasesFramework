package net.trentv.gasesframework.reaction;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class EntityReaction extends Reaction
{
	public abstract void react(Entity e);
}
