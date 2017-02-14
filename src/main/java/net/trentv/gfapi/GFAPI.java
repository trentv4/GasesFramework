package net.trentv.gfapi;

import javax.annotation.Nullable;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GFAPI
{
	private static final GFAPI INSTANCE = new GFAPI();
	public static final String OWNER = "gasesFramework";
	public static final String VERSION = "2.0.0";
	public static final String TARGTVERSION = "1.10.2";
	public static final String PROVIDES = "gasesFrameworkAPI";

	public static DamageSourceAsphyxiation damageSourceAsphyxiation = INSTANCE.new DamageSourceAsphyxiation("asphyxiation");
	public static final GasType AIR = new GasType("air", 0, 0, 0, Combustability.NONE);

	public static IManipulationAPI manipulationAPI;
	public static IRegistrationAPI registrationAPI;

	public static void install(IManipulationAPI a, IRegistrationAPI b)
	{
		manipulationAPI = a;
		registrationAPI = b;
	}

	public static boolean isInstalled()
	{
		return (manipulationAPI != null & registrationAPI != null);
	}

	private class DamageSourceAsphyxiation extends DamageSource
	{
		public DamageSourceAsphyxiation(String damageTypeIn)
		{
			super(damageTypeIn);
			this.setDamageBypassesArmor();
		}
	}
	
	/* Shorthand methods, just checks if the APIs exist and passes it through to the real API */
	
	// Registration API
	
	public static void registerGasType(GasType type, ResourceLocation location)
	{
		if(registrationAPI != null)
		{
			registrationAPI.registerGasType(type, location);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
		}
	}
	
	public static @Nullable GasType getGasType(ResourceLocation location)
	{
		if(registrationAPI != null)
		{
			return registrationAPI.getGasType(location);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
			return null;
		}
	}
	
	public static GasType[] getGasTypes()
	{
		if(registrationAPI != null)
		{
			return registrationAPI.getGasTypes();
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
			return new GasType[]{};
		}
	}
	
	// Manipulation API

	public static GasType getGasType(BlockPos pos, World access)
	{
		if(manipulationAPI != null)
		{
			return manipulationAPI.getGasType(pos, access);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
			return null;
		}
	}
	
	public static int getGasLevel(BlockPos pos, World access)
	{
		if(manipulationAPI != null)
		{
			return manipulationAPI.getGasLevel(pos, access);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
			return -1;
		}
	}

	public static boolean canPlaceGas(BlockPos pos, World access, @Nullable GasType currentGas)
	{
		if(manipulationAPI != null)
		{
			return manipulationAPI.canPlaceGas(pos, access, currentGas);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
			return false;
		}
	}

	public static void setGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if(manipulationAPI != null)
		{
			manipulationAPI.setGasLevel(pos, access, gas, level);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
		}
	}

	public static void removeGasLevel(BlockPos pos, World access, int level)
	{
		if(manipulationAPI != null)
		{
			manipulationAPI.removeGasLevel(pos, access, level);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
		}
	}

	public static int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		if(manipulationAPI != null)
		{
			return manipulationAPI.addGasLevel(pos, access, gas, level);
		}
		else
		{
			System.out.println("Gases Framework not installed. Try installing it!");
			return -1;
		}
	}
}
