package net.trentv.gases;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.trentv.gases.common.GasesCreativeTab;
import net.trentv.gasesframework.api.GFAPI;
import net.trentv.gasesframework.api.IGasesFrameworkImplementation;
import net.trentv.gasesframework.api.IGasesFrameworkRegistry;
import net.trentv.gasesframework.common.GasesFrameworkImplementation;
import net.trentv.gasesframework.common.GasesFrameworkRegistry;
import net.trentv.gasesframework.init.GasesFrameworkObjects;

@Mod(modid = Gases.MODID, version = Gases.VERSION, dependencies = "required-after:gasesFramework;", acceptedMinecraftVersions = "1.10.2")
public class Gases
{
	@Instance(Gases.MODID)
	public static Gases instance;

	public static final String MODID = "gasesframework";
	public static final String VERSION = "2.0.0";
	public static final GasesCreativeTab CREATIVE_TAB = new GasesCreativeTab("gases");

	@SidedProxy(clientSide = "net.trentv.gasesframework.client.ClientProxy", serverSide = "net.trentv.gasesframework.server.ServerProxy")
	public static CommonProxy proxy;

	public static Logger logger;

	public static IGasesFrameworkRegistry registry = GFAPI.registry;
	public static IGasesFrameworkImplementation implementation = GFAPI.implementation;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		// Register all items, blocks, and gases
		GasesObjects.init();
		proxy.registerRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerColorHandlers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
