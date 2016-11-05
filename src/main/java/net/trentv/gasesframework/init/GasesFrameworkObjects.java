package net.trentv.gasesframework.init;

import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustability;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.reaction.EntityReactionBlindness;
import net.trentv.gasesframework.api.reaction.EntityReactionHrrm;
import net.trentv.gasesframework.api.reaction.EntityReactionSlowness;
import net.trentv.gasesframework.api.reaction.EntityReactionSuffocation;

public class GasesFrameworkObjects
{
	public static final GasType SMOKE = new GasType(false, "smoke", 0xFFFFFF, 2, 1, Combustability.NONE)
	                                    .setCreativeTab(GasesFramework.CREATIVE_TAB)
	                                    .setCohesion(10)
	                                    .registerEntityReaction(new EntityReactionSuffocation(3, 3))
	                                    .registerEntityReaction(new EntityReactionBlindness(4))
	                                    .registerEntityReaction(new EntityReactionSlowness(2));
	public static final GasType gasTypeFire = new GasType(false, "fire", 0x7F4F4F7F, 2, 0, Combustability.NONE);

	public static void init()
	{
		GasesFramework.registry.registerGasType(SMOKE);
		
		GasType[] allTypes = GasesFramework.registry.getGastypes();
		for (GasType type : allTypes)
		{
			type.registerEntityReaction(new EntityReactionHrrm());
		}
	}
}
