package net.trentv.gasesframework.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.impl.GFRegistrationAPI;

public class IGasesModelLoader implements ICustomModelLoader
{
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager)
	{

	}

	@Override
	public boolean accepts(ResourceLocation modelLocation)
	{
		if (modelLocation.getResourcePath().contains("gas_"))
		{
			if (modelLocation.getResourcePath().contains("models/item/")) return true;
			GasType a = GFRegistrationAPI.getGasType(new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath()));
			if(a != null) return true;
		}
		return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception
	{
		ModelResourceLocation res = (ModelResourceLocation) modelLocation;
		if (modelLocation.getResourcePath().contains("gas_"))
		{
			System.out.println(modelLocation.toString());
			if ((res.getVariant().equals("inventory")))
			{
				return new ModelBlockGas(16);
			}
			else
			{
				int capacity = (int) Integer.valueOf(res.getVariant().replaceAll("capacity=", ""));
				return new ModelBlockGas(capacity);
			}
		}
		return null;
	}
}
