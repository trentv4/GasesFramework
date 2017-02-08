package net.trentv.gasesframework.impl;

import java.util.HashMap;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.common.block.BlockGas;
import net.trentv.gfapi.GasType;
import net.trentv.gfapi.IRegistrationAPI;

public class GFRegistrationAPI implements IRegistrationAPI
{
	private HashMap<ResourceLocation, GasType> gasTypes = new HashMap<ResourceLocation, GasType>();

	@Override
	public void registerGasType(GasType type, ResourceLocation location)
	{
		// Register the gas block
		BlockGas newGasBlock = new BlockGas(type);
		newGasBlock.setRegistryName(GasesFramework.MODID, "gas_" + type.name);
		GameRegistry.register(newGasBlock);

		type.block = newGasBlock;

		// Register the gas ItemBlock

		ItemBlock newGasItemBlock = new ItemBlock(newGasBlock);
		newGasItemBlock.setRegistryName(GasesFramework.MODID, "gas_" + type.name);
		GameRegistry.register(newGasItemBlock);

		type.itemBlock = newGasItemBlock;

		// Ensure the item gets sent off to the ClientProxy to be registered
		gasTypes.put(location, type);
	}

	@Override
	public GasType getGasType(ResourceLocation location)
	{
		return gasTypes.get(location);
	}

	@Override
	public GasType[] getGasTypes()
	{
		return gasTypes.values().toArray(new GasType[gasTypes.values().size()]);
	}
}
