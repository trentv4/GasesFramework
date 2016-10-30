package net.trentv.gasesframework.init;

import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustability;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.reaction.entityreactions.EntityReactionDamage;
import net.trentv.gasesframework.reaction.entityreactions.EntityReactionHrrm;

public class GasesFrameworkObjects
{
	public static final GasType SMOKE = new GasType(false, "smoke", 0x7F4F4F7F, 2, 0, Combustability.NONE)
	                                 .setCreativeTab(GasesFramework.CREATIVE_TAB)
	                                 .setCohesion(10)
	                                 .registerEntityReaction(new EntityReactionDamage(15, GasesFramework.damageSourceAsphyxiation, 2f));

	public static void init()
	{
		GasesFramework.registry.registerGasType(SMOKE);
		
		GasType[] allTypes = GasesFramework.registry.getGastypes();
		for(GasType type: allTypes)
		{
			type.registerEntityReaction(new EntityReactionHrrm());
		}
	}
}
