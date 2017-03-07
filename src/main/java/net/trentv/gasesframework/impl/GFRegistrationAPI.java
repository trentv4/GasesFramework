package net.trentv.gasesframework.impl;

import java.util.HashMap;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.client.GasesModelLoader;
import net.trentv.gasesframework.common.block.BlockGas;

public class GFRegistrationAPI
{
	private static HashMap<ResourceLocation, GasType> gasTypes = new HashMap<ResourceLocation, GasType>();

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
		
		GasesModelLoader.registeredLocations.add(location);
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
}
