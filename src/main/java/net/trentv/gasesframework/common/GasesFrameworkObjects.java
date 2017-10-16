package net.trentv.gasesframework.common;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionBlindness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionHrrm;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSlowness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSuffocation;
import net.trentv.gasesframework.common.block.BlockGas;

public class GasesFrameworkObjects
{
	public static final GasType SMOKE = new GasType("smoke", 0xAAAAAA, 2, 1, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB).setCohesion(10).registerEntityReaction(new EntityReactionSuffocation(3, 3)).registerEntityReaction(new EntityReactionBlindness(4)).registerEntityReaction(new EntityReactionSlowness(2));
	public static final GasType FIRE = new GasType("fire", 0x7F4F4F7F, 2, 0, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB).setCohesion(8).setDissipation(8, 8).setTexture(new ResourceLocation(GasesFramework.MODID, "block/gas_fire"), false);

	public static void init()
	{
		GFRegistrationAPI.registerGasType(SMOKE, new ResourceLocation(GasesFramework.MODID, "gas_" + SMOKE.name));
		GFRegistrationAPI.registerGasType(FIRE, new ResourceLocation(GasesFramework.MODID, "gas_" + FIRE.name));

		GasType[] allTypes = GFRegistrationAPI.getGasTypes();
		for (GasType type : allTypes)
		{
			type.registerEntityReaction(new EntityReactionHrrm());
		}

		GFRegistrationAPI.registerIgnitionSource(Blocks.FIRE.getDefaultState());
		GFRegistrationAPI.registerIgnitionSource(Blocks.LAVA.getDefaultState());
		GFRegistrationAPI.registerIgnitionSource(Blocks.FLOWING_LAVA.getDefaultState());
		GFRegistrationAPI.registerIgnitionSource(Blocks.TORCH.getDefaultState());
		GFRegistrationAPI.registerIgnitionSource(Blocks.LIT_FURNACE.getDefaultState());
		
		for(int i = 1; i <= 16; i++)
		{
			// The actual ignited gas
			GFRegistrationAPI.registerIgnitionSource(FIRE.block.getDefaultState().withProperty(BlockGas.CAPACITY, i));
		}
	}
}
