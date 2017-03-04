package net.trentv.gasesframework.init;

import net.minecraft.util.ResourceLocation;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionBlindness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionHrrm;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSlowness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSuffocation;
import net.trentv.gasesframework.impl.GFRegistrationAPI;

public class GasesFrameworkObjects
{
	public static final GasType SMOKE = new GasType("smoke", 0xFFFFFF, 2, 1, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB).setCohesion(10).registerEntityReaction(new EntityReactionSuffocation(3, 3)).registerEntityReaction(new EntityReactionBlindness(4)).registerEntityReaction(new EntityReactionSlowness(2));
	public static final GasType FIRE = new GasType("fire", 0x7F4F4F7F, 2, 0, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);

	// temporarily here until I do the split to Glenn's Gases
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, 0, Combustibility.EXPLOSIVE).setCreativeTab(GasesFramework.CREATIVE_TAB);

	public static void init()
	{
		GFRegistrationAPI.registerGasType(SMOKE, new ResourceLocation(GasesFramework.MODID, "gas_" + SMOKE.name));
		GFRegistrationAPI.registerGasType(FIRE, new ResourceLocation(GasesFramework.MODID, "gas_" + FIRE.name));
		GFRegistrationAPI.registerGasType(RED_GAS, new ResourceLocation(GasesFramework.MODID, "gas_" + RED_GAS.name));

		GasType[] allTypes = GFRegistrationAPI.getGasTypes();
		for (GasType type : allTypes)
		{
			type.registerEntityReaction(new EntityReactionHrrm());
		}
	}
}
