package net.trentv.gasesframework.impl;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.client.GasesModelLoader;
import net.trentv.gasesframework.common.block.BlockGas;

public class GFRegistrationAPI
{
	private static HashMap<ResourceLocation, GasType> gasTypes = new HashMap<ResourceLocation, GasType>();
	private static HashSet<Block> ignitionBlocks = new HashSet<Block>();
	
	public static void registerGasType(GasType type, ResourceLocation location)
	{
		// Register the gas block
		BlockGas newGasBlock = new BlockGas(type);
		newGasBlock.setRegistryName(location);
		GameRegistry.register(newGasBlock);

		type.block = newGasBlock;

		// Register the gas ItemBlock

		ItemBlock newGasItemBlock = new ItemBlock(newGasBlock);
		newGasItemBlock.setRegistryName(location);
		GameRegistry.register(newGasItemBlock);

		type.itemBlock = newGasItemBlock;
		
		// Rendering stuff - in the world, then in the inventory
		GasesModelLoader.registeredLocations.add(location);
		setInventoryResourceLocation(type);

		// Finally, we can track it in the *right* place.
		gasTypes.put(location, type);
	}

	public static GasType getGasType(ResourceLocation location)
	{
		GasType r = gasTypes.get(location);
		return r;
	}

	public static GasType[] getGasTypes()
	{
		return gasTypes.values().toArray(new GasType[gasTypes.values().size()]);
	}
	
	public static void registerIgnitionBlock(Block block)
	{
		ignitionBlocks.add(block);
	}
	
	public static boolean isIgnitionBlock(Block block)
	{
		return ignitionBlocks.contains(block);
	}

	@SideOnly(Side.CLIENT)
	private static final void setInventoryResourceLocation(GasType type)
	{
		ModelLoader.setCustomModelResourceLocation(type.itemBlock, 0, new ModelResourceLocation(type.itemBlock.getRegistryName(), "inventory"));
	}
}
