package net.trentv.gasesframework.init;

import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustability;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.reaction.reactions.EntityReactionDamage;

public class GasesFrameworkObjects
{
	public static final GasType SMOKE = new GasType(false, "smoke", 0x7F4F4F7F, 2, 0, Combustability.NONE)
	                                 .setCreativeTab(GasesFramework.CREATIVE_TAB)
	                                 .setCohesion(10)
	                                 .registerEntityReaction(new EntityReactionDamage(GasesFramework.damageSourceAsphyxiation, 6f));

	public static void init()
	{
		GasesFramework.registry.registerGasType(SMOKE);
	}
}
