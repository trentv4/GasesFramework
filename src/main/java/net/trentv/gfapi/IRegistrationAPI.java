package net.trentv.gfapi;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;

public interface IRegistrationAPI
{
	/**
	 * Registers a gastype to the matching resourcelocation. The gastype *should* be
	 * finalized by this point.
	 */
	public void registerGasType(GasType type, ResourceLocation location);
	
	/**
	 * @return Registered gastype that matches location. This may be null.
	 */
	public @Nullable GasType getGasType(ResourceLocation location);

	/**
	 * @return All registered gases
	 */
	public GasType[] getGasTypes();
}
