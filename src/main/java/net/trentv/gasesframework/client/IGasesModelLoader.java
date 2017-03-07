package net.trentv.gasesframework.client;

import java.util.HashSet;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class IGasesModelLoader implements ICustomModelLoader
{
	public static HashSet<ResourceLocation> registeredLocations = new HashSet<ResourceLocation>();
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager)
	{

	}

	@Override
	public boolean accepts(ResourceLocation modelLocation)
	{
		if (registeredLocations.contains(new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath())))
		{
			return true;
		}
		return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception
	{
		ModelResourceLocation res = (ModelResourceLocation) modelLocation;
		if ((res.getVariant().equals("inventory")))
		{
			return new ModelBlockGas(16);
		}
		else
		{
			//Yeah, sure. Good code. It's only run every time the model loader reloads. 
			//Not like that happens a minimum of two times...
			int capacity = (int) Integer.valueOf(res.getVariant().replaceAll("capacity=", ""));
			return new ModelBlockGas(capacity);
		}
	}
}
