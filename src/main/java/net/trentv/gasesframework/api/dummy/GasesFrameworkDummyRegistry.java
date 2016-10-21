package net.trentv.gasesframework.api.dummy;

import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.IGasesFrameworkRegistry;

public class GasesFrameworkDummyRegistry implements IGasesFrameworkRegistry
{
	@Override
	public void registerGasType(GasType type)
	{

	}

	@Override
	public GasType[] getGastypes()
	{
		return null;
	}

	@Override
	public GasType getGasTypeByName(String name)
	{
		return null;
	}
}
