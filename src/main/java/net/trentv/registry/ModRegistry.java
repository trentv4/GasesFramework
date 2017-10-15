package net.trentv.registry;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModRegistry
{
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<Biome> biomes = new ArrayList<Biome>();

	// Utility methods

	public static void registerBlock(Block... toRegister)
	{
		for (Block in : toRegister)
		{
			blocks.add(in);
		}
	}

	public static void registerBiome(Biome... toRegister)
	{
		for (Biome in : toRegister)
		{
			biomes.add(in);
		}
	}

	public static void registerBlockAndItem(Block... toRegister)
	{
		for (Block in : toRegister)
		{
			blocks.add(in);
			ItemBlock a = new ItemBlock(in);
			a.setRegistryName(in.getRegistryName());
			items.add(a);
		}
	}

	public static void registerItem(Item... toRegister)
	{
		for (Item in : toRegister)
		{
			items.add(in);
		}
	}
	
	// Events
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for (Block block : blocks)
		{
			event.getRegistry().register(block);
		}
	}

	@SubscribeEvent
	public void registerBiomes(RegistryEvent.Register<Biome> event)
	{
		for (Biome biome : biomes)
		{
			event.getRegistry().register(biome);
		}
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event)
	{
		for (Item item : items)
		{
			event.getRegistry().register(item);
		}
	}
}
