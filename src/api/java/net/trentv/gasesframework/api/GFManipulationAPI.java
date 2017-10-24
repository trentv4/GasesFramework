package net.trentv.gasesframework.api;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GFManipulationAPI
{
	public interface IManipulationAPI
	{
		public GasType getGasType(BlockPos pos, World access);

		public int getGasLevel(BlockPos pos, World access);

		public boolean canPlaceGas(BlockPos pos, World access, GasType currentGas);

		public void setGasLevel(BlockPos pos, World access, GasType gas, int level);

		public void removeGasLevel(BlockPos pos, World access, int level);

		public int addGasLevel(BlockPos pos, World access, GasType gas, int level);

		public void addGasLevelOverflow(BlockPos pos, World world, GasType gas, int level);
	}

	public static IManipulationAPI instance;

	public static GasType getGasType(BlockPos pos, World access)
	{
		if (instance != null)
		{
			return instance.getGasType(pos, access);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Manipulation API is not installed! GasesFramework is probably missing!");
			return null;
		}
	}

	public static int getGasLevel(BlockPos pos, World access)
	{
		if (instance != null)
		{
			return instance.getGasLevel(pos, access);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Manipulation API is not installed! GasesFramework is probably missing!");
			return 0;
		}
	}

	public static boolean canPlaceGas(BlockPos pos, World access, GasType currentGas)
	{
		if (instance != null)
		{
			return instance.canPlaceGas(pos, access, currentGas);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Manipulation API is not installed! GasesFramework is probably missing!");
			return false;
		}
	}

	public static void setGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if (instance != null)
		{
			instance.setGasLevel(pos, access, gas, level);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Manipulation API is not installed! GasesFramework is probably missing!");
		}
	}

	public static void removeGasLevel(BlockPos pos, World access, int level)
	{
		if (instance != null)
		{
			instance.removeGasLevel(pos, access, level);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Manipulation API is not installed! GasesFramework is probably missing!");
		}
	}

	public static int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if (instance != null)
		{
			return instance.addGasLevel(pos, access, gas, level);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Manipulation API is not installed! GasesFramework is probably missing!");
			return 0;
		}
	}

	public static void addGasLevelOverflow(BlockPos pos, World world, GasType gas, int level)
	{
		if (instance != null)
		{
			instance.addGasLevelOverflow(pos, world, gas, level);
		}
		else
		{
			GasesFrameworkAPI.LOGGER.warn("GF Manipulation API is not installed! GasesFramework is probably missing!");
		}
	}
}
