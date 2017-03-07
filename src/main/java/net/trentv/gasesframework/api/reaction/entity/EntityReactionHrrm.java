package net.trentv.gasesframework.api.reaction.entity;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.api.GasType;

public class EntityReactionHrrm implements IEntityReaction
{
	private static final String[] villagers = new String[] { "b3cea104-fd35-4a5d-bb7b-00668f133e28", // Dethridgecraft
			"1454841b-ba54-4071-80c0-5b51c2f2d25d", // Wyld
			"af63c94d-5dbe-4a34-b779-3d44fd9edb2a", // CrustyMustard
			"cbd1f8e6-3501-4918-8a1c-4610a2d7d478", // GlennA
			"5046ff0c-8e31-4705-9f09-521b29e99878", // Trentv4
			"f37f4fe1-e3ca-40b5-946f-fd66eefee179", // Username720
			"7dd55e6b-f16b-4804-8df5-2f50f7191b2d", // LividCoffee
			"e364e50e-11bc-402c-a07f-5bc96a8084ca", // Sips
			"2c6d7b3d-7674-4b18-8d86-bb6a707ea8e4", // Xephos
			"d51754f4-b279-4b18-a536-264bf3b02440", // Sjin
			"117de7ec-f75a-4d1f-9a8b-20e1f1b642ae", // MrCodeWarrior
			"ff65ca82-f788-4308-96ad-ce2e5c513d6f", // McJty
			"85ee97a4-0d2c-4221-b152-baea5fb8201c", // Romelo333 (McJty's son)
			"a2ff5ed5-81c5-4c5a-a22e-a16403c6eb79", // Morpheus1101
			"88419b6a-c0f5-451c-913c-57e111d9a07c", // CleverPanda714
			"7b794048-f144-40bb-83dd-4789a5cf7cc8", // TheRealp455w0rd
			"6989aa02-f8d0-4845-b205-2ba37b55c954", // RougeLogic
	};

	@Override
	public void react(Entity e, IBlockAccess access, GasType gas, BlockPos pos)
	{
		if (e instanceof EntityPlayer)
		{
			UUID q = e.getUniqueID();
			for (String s : villagers)
			{
				if (q.equals(UUID.fromString(s)))
				{
					e.playSound(SoundEvents.ENTITY_VILLAGER_AMBIENT, 1.0f, 1.0f);
				}
			}
		}
	}
}
