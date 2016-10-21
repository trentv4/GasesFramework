package net.trentv.gasesframework.reaction.reactions;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.trentv.gasesframework.reaction.EntityReaction;

public class EntityReactionHrrm extends EntityReaction
{
	private static final Random r = new Random();
	private static final String[] villagers = new String[]{
			"cyanideepic",
			"dethridgecraft",
			"wyld",
			"crustymustard",
			"glenna",
			"trentv4",
			"username720",
			"lividcoffee",
			"sips",
			"xephos",
			"sjin"
	};
	
	@Override
	public void react(Entity e)
	{
		// This should be changed to UUIDs
		if(e instanceof EntityPlayer)
		{
			String displayName = e.getDisplayName().getUnformattedText().toLowerCase();
			for(String s : villagers)
			{
				if(displayName.equals(s) & r.nextInt(10) == 0)
				{
					e.playSound(SoundEvents.ENTITY_VILLAGER_AMBIENT, 1.0f, 1.0f);
				}
			}
		}
	}
}
