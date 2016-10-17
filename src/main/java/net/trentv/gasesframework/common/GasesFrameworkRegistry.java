package net.trentv.gasesframework.common;

import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.IGasesFrameworkRegistry;
import net.trentv.gasesframework.client.ClientProxy;
import net.trentv.gasesframework.common.block.BlockGas;

public class GasesFrameworkRegistry implements IGasesFrameworkRegistry
{
	private HashMap<String, GasType> gasTypes = new HashMap<String, GasType>();
	
	@Override
	public void registerGasType(GasType type)
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
		gasTypes.put(type.name, type);

		if (type.isIndustrial)
		{
			// register lanterns and pipes
		}
	}
	
	public GasType[] getGastypes()
	{
		return gasTypes.values().toArray(new GasType[gasTypes.values().size()]);
	}
	
	@Nullable
	public GasType getGasTypeByName(String name)
	{
		return gasTypes.get(name);
	}
}
