package net.trentv.gasesframework.api;

import javax.annotation.Nullable;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.impl.GFManipulationAPI;
import net.trentv.gasesframework.impl.GFRegistrationAPI;

public class GFAPI
{
	private static final GFAPI INSTANCE = new GFAPI();
	public static final String OWNER = "gasesFramework";
	public static final String VERSION = "2.0.0";
	public static final String TARGTVERSION = "1.10.2";
	public static final String PROVIDES = "gasesFrameworkAPI";

	public static DamageSourceAsphyxiation damageSourceAsphyxiation = INSTANCE.new DamageSourceAsphyxiation("asphyxiation");
	public static final GasType AIR = new GasType("air", 0, 0, 0, Combustability.NONE);

	private class DamageSourceAsphyxiation extends DamageSource
	{
		public DamageSourceAsphyxiation(String damageTypeIn)
		{
			super(damageTypeIn);
			this.setDamageBypassesArmor();
		}
	}

	/*
	 * Shorthand methods, just checks if the APIs exist and passes it through to
	 * the real API
	 */

	// Registration API

	public static void registerGasType(GasType type, ResourceLocation location)
	{
		GFRegistrationAPI.registerGasType(type, location);
	}

	public static @Nullable GasType getGasType(ResourceLocation location)
	{
		return GFRegistrationAPI.getGasType(location);
	}

	public static GasType[] getGasTypes()
	{
		return GFRegistrationAPI.getGasTypes();
	}

	// Manipulation API

	public static GasType getGasType(BlockPos pos, World access)
	{
		return GFManipulationAPI.getGasType(pos, access);
	}

	public static int getGasLevel(BlockPos pos, World access)
	{
		return GFManipulationAPI.getGasLevel(pos, access);
	}

	public static boolean canPlaceGas(BlockPos pos, World access, @Nullable GasType currentGas)
	{
		return GFManipulationAPI.canPlaceGas(pos, access, currentGas);
	}

	public static void setGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		GFManipulationAPI.setGasLevel(pos, access, gas, level);
	}

	public static void removeGasLevel(BlockPos pos, World access, int level)
	{
		GFManipulationAPI.removeGasLevel(pos, access, level);
	}

	public static int addGasLevel(BlockPos pos, World access, GasType gas, int level)
	{
		return GFManipulationAPI.addGasLevel(pos, access, gas, level);
	}
}
