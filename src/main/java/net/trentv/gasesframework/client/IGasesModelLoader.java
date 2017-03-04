package net.trentv.gasesframework.client;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.GFAPI;
import net.trentv.gasesframework.api.GasType;

public class IGasesModelLoader implements ICustomModelLoader
{
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager)
	{

	}

	@Override
	public boolean accepts(ResourceLocation modelLocation)
	{
		List<String> path = Arrays.asList("gas_smoke");
		if (modelLocation.getResourceDomain().equals(GasesFramework.MODID))
		{
			if (modelLocation.getResourcePath().contains("gas_"))
			{
				GasType a = GFAPI.getGasType(modelLocation);
				return true;
			}
		}
		return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception
	{
		if (modelLocation instanceof ModelResourceLocation)
		{
			if (modelLocation.getResourceDomain().equals(GasesFramework.MODID))
			{
				if (modelLocation.getResourcePath().contains("gas_"))
				{
					if (((ModelResourceLocation) modelLocation).getVariant().equals("inventory"))
					{
						return new ModelBlockGas(16);
					}
					else
					{
						return new ModelBlockGas((int) Integer.valueOf(((ModelResourceLocation) modelLocation).getVariant().replaceAll("capacity=", "")));
					}
				}
			}
		}
		return null;
	}
}
