package net.trentv.gasesframework.api;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.trentv.gasesframework.GasesFrameworkRegistry;
import net.trentv.gasesframework.client.GasesModelLoader;
import net.trentv.gasesframework.common.block.BlockGas;

public class GFRegistrationAPI
{
	private static HashMap<ResourceLocation, GasType> gasTypes = new HashMap<ResourceLocation, GasType>();
	private static HashSet<IBlockState> ignitionSources = new HashSet<IBlockState>();

	public static void registerGasType(GasType type, ResourceLocation location)
	{
		type.setRegistryName(location);
		// Register the gas block
		BlockGas newGasBlock = new BlockGas(type);
		newGasBlock.setRegistryName(location);
		GasesFrameworkRegistry.registerBlock(newGasBlock);

		type.block = newGasBlock;

		// Register the gas ItemBlock

		ItemBlock newGasItemBlock = new ItemBlock(newGasBlock);
		newGasItemBlock.setRegistryName(location);
		GasesFrameworkRegistry.registerItem(newGasItemBlock);

		type.itemBlock = newGasItemBlock;

		// Rendering stuff - in the world, then in the inventory
		GasesModelLoader.registeredLocations.add(location);

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

	public static void registerIgnitionSource(IBlockState state)
	{
		ignitionSources.add(state);
	}

	public static boolean isIgnitionSource(IBlockState state)
	{
		return ignitionSources.contains(state);
	}
}
