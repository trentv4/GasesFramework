package net.trentv.gasesframework.api.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class GasEffectsProvider implements ICapabilitySerializable
{
	@CapabilityInject(IGasEffects.class)
	public static Capability<IGasEffects> GAS_EFFECTS;

	public IGasEffects instance = GAS_EFFECTS.getDefaultInstance();
	public GasEffectsStorage<IGasEffects> storage = new GasEffectsStorage<IGasEffects>();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == GAS_EFFECTS;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == GAS_EFFECTS)
		{
			return (T) instance;
		}
		return null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return storage.writeNBT(GAS_EFFECTS, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		storage.readNBT(GAS_EFFECTS, instance, null, nbt);
	}
}
