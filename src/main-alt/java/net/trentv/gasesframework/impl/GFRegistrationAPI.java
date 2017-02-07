package net.trentv.gasesframework.impl;

import net.minecraft.util.ResourceLocation;
import net.trentv.gfapi.GasType;
import net.trentv.gfapi.IRegistrationAPI;

public class GFRegistrationAPI implements IRegistrationAPI
{
	@Override
	public void registerGasType(GasType type, ResourceLocation location)
	{
		
	}

	@Override
	public GasType getGasType(ResourceLocation location)
	{
		return null;
	}

	@Override
	public GasType[] getGasTypes(String modid)
	{
		return null;
	}

	@Override
	public GasType[] getGasTypes()
	{
		return null;
	}
}
