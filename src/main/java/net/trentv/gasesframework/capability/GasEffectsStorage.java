package net.trentv.gasesframework.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class GasEffectsStorage implements IStorage<IGasEffectsCapability>
{
	public static final GasEffectsStorage storage = new GasEffectsStorage();
	
	@Override
	public NBTBase writeNBT(Capability<IGasEffectsCapability> capability, IGasEffectsCapability instance, EnumFacing side)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("gases-suffocation", instance.getSuffocation());
		nbt.setInteger("gases-blindness", instance.getBlindness());
		nbt.setInteger("gases-slowness", instance.getSlowness());
		return nbt;
	}

	@Override
	public void readNBT(Capability<IGasEffectsCapability> capability, IGasEffectsCapability instance, EnumFacing side, NBTBase nbt)
	{
		NBTTagCompound a = (NBTTagCompound) nbt;
		instance.setSuffocation((int) a.getInteger("gases-suffocation"));
		instance.setBlindness((int) a.getInteger("gases-blindness"));
		instance.setSlowness((int) a.getInteger("gases-slowness"));
	}
}
