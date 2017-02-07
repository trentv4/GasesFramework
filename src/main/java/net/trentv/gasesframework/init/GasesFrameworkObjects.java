package net.trentv.gasesframework.init;

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
	public static final GasType SMOKE = new GasType(false, "smoke", 0xFFFFFF, 2, 1, Combustability.NONE)
	                                    .setCreativeTab(GasesFramework.CREATIVE_TAB)
	                                    .setCohesion(10)
	                                    .registerEntityReaction(new EntityReactionSuffocation(3, 3))
	                                    .registerEntityReaction(new EntityReactionBlindness(4))
	                                    .registerEntityReaction(new EntityReactionSlowness(2));
	public static final GasType gasTypeFire = new GasType(false, "fire", 0x7F4F4F7F, 2, 0, Combustability.NONE);

	public static void init()
	{
		GasesFramework.implementation.registerGasType(SMOKE);
		
		GasType[] allTypes = GFAPI.implementation.getGastypes();
		for (GasType type : allTypes)
		{
			type.registerEntityReaction(new EntityReactionHrrm());
		}
	}
}
