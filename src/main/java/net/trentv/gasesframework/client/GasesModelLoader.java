package net.trentv.gasesframework.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.impl.ImplRegistrationAPI;

public class GasesModelLoader implements ICustomModelLoader
{
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager)
	{

	}

	@Override
	public boolean accepts(ResourceLocation modelLocation)
	{
		if (ImplRegistrationAPI.registeredGasTypeLocations.contains(convert(modelLocation)))
		{
			return true;
		}
		return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception
	{
		ModelResourceLocation res = (ModelResourceLocation) modelLocation;
		GasType a = GFRegistrationAPI.getGasType(convert(modelLocation));
		if ((res.getVariant().equals("inventory")))
		{
			return new ModelBlockGas(16, a);
		}
		else
		{
			// Yeah, sure. Good code. It's only run every time the model loader reloads.
			// Not like that happens a minimum of two times...
			int capacity = Integer.valueOf(res.getVariant().replaceAll("capacity=", ""));
			return new ModelBlockGas(capacity, a);
		}
	}

	private ResourceLocation convert(ResourceLocation in)
	{
		return new ResourceLocation(in.getNamespace(), in.getPath());
	}
}
