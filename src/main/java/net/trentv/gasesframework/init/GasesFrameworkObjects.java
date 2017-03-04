package net.trentv.gasesframework.init;

import net.minecraft.util.ResourceLocation;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustability;
import net.trentv.gasesframework.api.GFAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionBlindness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionHrrm;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSlowness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSuffocation;

public class GasesFrameworkObjects
{
	public static final GasType SMOKE = new GasType("smoke", 0xFFFFFF, 2, 1, Combustability.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB).setCohesion(10).registerEntityReaction(new EntityReactionSuffocation(3, 3)).registerEntityReaction(new EntityReactionBlindness(4)).registerEntityReaction(new EntityReactionSlowness(2));
	public static final GasType FIRE = new GasType("fire", 0x7F4F4F7F, 2, 0, Combustability.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);

	// temporarily here until I do the split to Glenn's Gases
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, 0, Combustability.EXPLOSIVE).setCreativeTab(GasesFramework.CREATIVE_TAB);

	public static void init()
	{
		GFAPI.registerGasType(SMOKE, new ResourceLocation(GasesFramework.MODID, "gas_" + SMOKE.name));
		GFAPI.registerGasType(FIRE, new ResourceLocation(GasesFramework.MODID, "gas_" + FIRE.name));
		GFAPI.registerGasType(RED_GAS, new ResourceLocation(GasesFramework.MODID, "gas_" + RED_GAS.name));

		GasType[] allTypes = GFAPI.getGasTypes();
		for (GasType type : allTypes)
		{
			type.registerEntityReaction(new EntityReactionHrrm());
		}
	}
}
