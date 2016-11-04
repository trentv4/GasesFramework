package net.trentv.gasesframework.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class GasEffectsStorage<T extends IGasEffects> implements IStorage<IGasEffects>
{
	@Override
	public NBTBase writeNBT(Capability<IGasEffects> capability, IGasEffects instance, EnumFacing side)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("gases-breath", instance.getSuffocation());
		nbt.setFloat("gases-blindness", instance.getBlindness());
		nbt.setFloat("gases-slowness", instance.getSlowness());
		return nbt;
	}

	@Override
	public void readNBT(Capability<IGasEffects> capability, IGasEffects instance, EnumFacing side, NBTBase nbt)
	{
		NBTTagCompound a = (NBTTagCompound) nbt;
		instance.setSuffocation(a.getInteger("gases-breath"));
		instance.setBlindness(a.getInteger("gases-blindness"));
		instance.setSlowness(a.getInteger("gases-slowness"));
	}
}
