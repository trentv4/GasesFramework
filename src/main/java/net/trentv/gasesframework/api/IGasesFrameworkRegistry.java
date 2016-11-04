package net.trentv.gasesframework.api;

import javax.annotation.Nullable;

public interface IGasesFrameworkRegistry
{
	public void registerGasType(GasType type);

	public GasType[] getGastypes();

	@Nullable
	public GasType getGasTypeByName(String name);
}
