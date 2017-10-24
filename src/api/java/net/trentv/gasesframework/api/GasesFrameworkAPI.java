package net.trentv.gasesframework.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class GasesFrameworkAPI
{
	public static final String API_VERSION = "2.0.0";
	public static DamageSourceAsphyxiation damageSourceAsphyxiation = new DamageSourceAsphyxiation("asphyxiation");
	public static final GasType AIR = new GasType("air", 0, 0, 0, Combustibility.NONE).setRegistryName(new ResourceLocation("gasesframeworkapi:air"));
	public static final Logger LOGGER = LogManager.getLogger("gasesframeworkapi");

	private static class DamageSourceAsphyxiation extends DamageSource
	{
		public DamageSourceAsphyxiation(String damageTypeIn)
		{
			super(damageTypeIn);
			this.setDamageBypassesArmor();
		}
	}
}
