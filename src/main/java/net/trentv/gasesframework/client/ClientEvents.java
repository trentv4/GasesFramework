package net.trentv.gasesframework.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.gasesframework.api.capability.IGasEffects;
import net.trentv.gasesframework.common.capability.GasEffectsProvider;

public class ClientEvents
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onFogDensity(FogDensity event)
	{
		Entity a = event.getEntity();
		if (a.hasCapability(GasEffectsProvider.GAS_EFFECTS, null))
		{
			IGasEffects q = a.getCapability(GasEffectsProvider.GAS_EFFECTS, null);
			float f = q.getBlindness() / 250.0f;

			if (f > 0.0f)
			{
				event.setDensity(f * f);
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
				event.setCanceled(true);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onFogColor(FogColors event)
	{
		Entity a = event.getEntity();
		if (a.hasCapability(GasEffectsProvider.GAS_EFFECTS, null))
		{
			IGasEffects q = a.getCapability(GasEffectsProvider.GAS_EFFECTS, null);

			float f = 1 - (q.getBlindness() / 250);
			event.setRed(event.getRed() * f);
			event.setGreen(event.getGreen() * f);
			event.setBlue(event.getBlue() * f);
		}
	}
}
