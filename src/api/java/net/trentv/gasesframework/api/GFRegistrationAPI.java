package net.trentv.gasesframework.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class GFRegistrationAPI
{
	public interface IRegistrationAPI
	{
		public void registerGasType(GasType type, ResourceLocation location);

		public GasType getGasType(ResourceLocation location);

		public GasType[] getGasTypes();

		public void registerIgnitionSource(IBlockState state);

		public boolean isIgnitionSource(IBlockState state);
	}

	public static IRegistrationAPI instance;

	public static void registerGasType(GasType type, ResourceLocation location)
	{
		if (instance != null)
		{
			instance.registerGasType(type, location);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Registration API is not installed! GasesFramework is probably missing!");
		}
	}

	public static GasType getGasType(ResourceLocation location)
	{
		if (instance != null)
		{
			return instance.getGasType(location);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Registration API is not installed! GasesFramework is probably missing!");
			return null;
		}
	}

	public static GasType[] getGasTypes()
	{
		if (instance != null)
		{
			return instance.getGasTypes();
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Registration API is not installed! GasesFramework is probably missing!");
			return null;
		}
	}

	public static void registerIgnitionSource(IBlockState state)
	{
		if (instance != null)
		{
			instance.registerIgnitionSource(state);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Registration API is not installed! GasesFramework is probably missing!");
		}
	}

	public static boolean isIgnitionSource(IBlockState state)
	{
		if (instance != null)
		{
			return instance.isIgnitionSource(state);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Registration API is not installed! GasesFramework is probably missing!");
			return false;
		}
	}
}
