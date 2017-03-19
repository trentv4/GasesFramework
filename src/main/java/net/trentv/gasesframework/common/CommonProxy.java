package net.trentv.gasesframework.common;

import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
	public abstract void registerRenderers();

	public abstract void registerColorHandlers();

	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
	}
}