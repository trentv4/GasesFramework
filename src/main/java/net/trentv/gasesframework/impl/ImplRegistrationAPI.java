package net.trentv.gasesframework.impl;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.trentv.gasesframework.GasesFrameworkRegistry;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.common.block.BlockGas;

public class ImplRegistrationAPI implements GFRegistrationAPI.IRegistrationAPI
{
	public ImplRegistrationAPI()
	{
		GFRegistrationAPI.instance = this;
	}

	private static HashMap<ResourceLocation, GasType> gasTypes = new HashMap<ResourceLocation, GasType>();
	private static HashSet<IBlockState> ignitionSources = new HashSet<IBlockState>();
	public static HashSet<ResourceLocation> registeredGasTypeLocations = new HashSet<ResourceLocation>();

	@Override
	public void registerGasType(GasType type, ResourceLocation location)
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
		registeredGasTypeLocations.add(location);

		// Finally, we can track it in the *right* place.
		gasTypes.put(location, type);
	}

	@Override
	public GasType getGasType(ResourceLocation location)
	{
		GasType r = gasTypes.get(location);
		return r;
	}

	@Override
	public GasType[] getGasTypes()
	{
		return gasTypes.values().toArray(new GasType[gasTypes.values().size()]);
	}

	@Override
	public void registerIgnitionSource(IBlockState state)
	{
		ignitionSources.add(state);
	}

	@Override
	public boolean isIgnitionSource(IBlockState state)
	{
		return ignitionSources.contains(state);
	}
}
