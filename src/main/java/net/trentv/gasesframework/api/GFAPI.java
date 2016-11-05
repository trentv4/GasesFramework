package net.trentv.gasesframework.api;

import net.trentv.gasesframework.api.dummy.GasesFrameworkDummyImplementation;
import net.trentv.gasesframework.api.dummy.GasesFrameworkDummyRegistry;

public class GFAPI
{
	public static final String OWNER = "gasesFramework";
	public static final String VERSION = "2.0.0";
	public static final String TARGTVERSION = "1.10.2";
	public static final String PROVIDES = "gasesFrameworkAPI";

	private static boolean isInstalled = false;

	public static IGasesFrameworkImplementation implementation = new GasesFrameworkDummyImplementation();
	public static IGasesFrameworkRegistry registry = new GasesFrameworkDummyRegistry();

	public static DamageSourceAsphyxiation damageSourceAsphyxiation = new DamageSourceAsphyxiation("asphyxiation");

	public static final GasType gasTypeAir = new GasType(false, "air", 0, 0, 0, Combustability.NONE);

	public static void install(IGasesFrameworkImplementation implementation, IGasesFrameworkRegistry registry)
	{
		GFAPI.implementation = implementation;
		GFAPI.registry = registry;
		isInstalled = true;
	}

	public static boolean isInstalled()
	{
		return isInstalled;
	}
}
