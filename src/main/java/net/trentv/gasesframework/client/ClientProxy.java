package net.trentv.gasesframework.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.trentv.gasesframework.CommonProxy;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.impl.GFRegistrationAPI;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		GasType[] types = GFRegistrationAPI.getGasTypes();
		for (int i = 0; i < types.length; i++)
		{
			ItemBlock item = types[i].itemBlock;
			// Register the item so it displays as a proper block in inventory
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		ModelLoaderRegistry.registerLoader(new GasesModelLoader());
	}

	@Override
	public void registerColorHandlers()
	{
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
		GasType[] types = GFRegistrationAPI.getGasTypes();
		for (int i = 0; i < types.length; i++)
		{
			ItemBlock item = types[i].itemBlock;
			Block block = types[i].block;
			// Register the gastype for both the item and the block so they can
			// be tinted at runtime
			blockColors.registerBlockColorHandler(types[i].getGasColor(), block);
			itemColors.registerItemColorHandler(types[i].getGasColor(), item);
		}
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}
}
