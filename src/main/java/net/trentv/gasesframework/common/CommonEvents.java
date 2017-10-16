package net.trentv.gasesframework.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.capability.IGasEffects;
import net.trentv.gasesframework.common.capability.GasEffectsProvider;

public class CommonEvents
{
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if (!event.getEntity().getEntityWorld().isRemote | true)
		{
			Entity b = event.getEntity();
			if (b.hasCapability(GasEffectsProvider.GAS_EFFECTS, null))
			{
				IGasEffects q = b.getCapability(GasEffectsProvider.GAS_EFFECTS, null);

				if (q.getSuffocation() == 200)
				{
					b.attackEntityFrom(GasesFramework.damageSourceAsphyxiation, 2);
				}

				if (q.getSuffocation() > 0)
					q.setSuffocation(q.getSuffocation() - 1);
				if (q.getBlindness() > 0)
					q.setBlindness(q.getBlindness() - 1);

				if (q.getSlowness() > 0)
				{
					q.setSlowness(q.getSlowness() - 1);
					float multiply = 1 - (q.getSlowness() / 100);
					if (multiply < 0.05f)
						multiply = 0.05f;
					b.motionX *= multiply;
					b.motionZ *= multiply;
				}
			}
		}
	}

	@SubscribeEvent
	public void AttachCapabilityEventEntity(AttachCapabilitiesEvent<Entity> e)
	{
		if (e.getObject() instanceof EntityLivingBase)
		{
			e.addCapability(new ResourceLocation(GasesFramework.MODID, "gas_effects"), new GasEffectsProvider());
		}
	}
}
