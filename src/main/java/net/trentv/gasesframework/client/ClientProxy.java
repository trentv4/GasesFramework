package net.trentv.gasesframework.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
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
			if(types[i].tintindex)
			{
				blockColors.registerBlockColorHandler(types[i].getGasColor(), block);
				itemColors.registerItemColorHandler(types[i].getGasColor(), item);
			}
		}
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}
}
