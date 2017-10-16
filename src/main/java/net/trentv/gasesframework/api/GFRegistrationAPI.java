package net.trentv.gasesframework.api;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.gasesframework.client.GasesModelLoader;
import net.trentv.gasesframework.common.block.BlockGas;
import net.trentv.registry.ModRegistry;

public class GFRegistrationAPI
{
	private static HashMap<ResourceLocation, GasType> gasTypes = new HashMap<ResourceLocation, GasType>();
	private static HashSet<IBlockState> ignitionSources = new HashSet<IBlockState>();

	public static void registerGasType(GasType type, ResourceLocation location)
	{
		// Register the gas block
		BlockGas newGasBlock = new BlockGas(type);
		newGasBlock.setRegistryName(location);
		ModRegistry.registerBlock(newGasBlock);

		type.block = newGasBlock;

		// Register the gas ItemBlock

		ItemBlock newGasItemBlock = new ItemBlock(newGasBlock);
		newGasItemBlock.setRegistryName(location);
		ModRegistry.registerItem(newGasItemBlock);

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

	public static void registerIgnitionSource(IBlockState state)
	{
		ignitionSources.add(state);
	}

	public static boolean isIgnitionSource(IBlockState state)
	{
		return ignitionSources.contains(state);
	}

	@SideOnly(Side.CLIENT)
	private static final void setInventoryResourceLocation(GasType type)
	{
		ModelLoader.setCustomModelResourceLocation(type.itemBlock, 0, new ModelResourceLocation(type.itemBlock.getRegistryName(), "inventory"));
	}
}
