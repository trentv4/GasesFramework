package net.trentv.gasesframework;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEvents
{
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		NBTTagCompound a = event.getEntityLiving().getEntityData();
		int level = a.getInteger("gases-suffocation");
		if(level > 0) a.setInteger("gases-suffocation", level - 1);
		System.out.println(level);
	}
}
