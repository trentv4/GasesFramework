package net.trentv.gasesframework.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class GasEffectsProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{
	@CapabilityInject(IGasEffectsCapability.class)
	public static final Capability<IGasEffectsCapability> gasEffects = null;
	public IGasEffectsCapability instance = new DefaultGasEffectsCapability();
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		return (NBTTagCompound) GasEffectsStorage.storage.writeNBT(gasEffects, instance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		GasEffectsStorage.storage.writeNBT(gasEffects, instance, null);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == gasEffects;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return (T) gasEffects;
	}
}
