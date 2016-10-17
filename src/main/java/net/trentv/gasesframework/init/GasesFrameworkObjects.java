package net.trentv.gasesframework.init;

import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.common.Combustability;

public class GasesFrameworkObjects
{
	public static final GasType SMOKE = new GasType(false, "smoke", 0x7F4F4F7F, 2, 0, Combustability.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB).setCohesion(10);

	public static void init()
	{
		GasesFramework.registry.registerGasType(SMOKE);
	}
}
