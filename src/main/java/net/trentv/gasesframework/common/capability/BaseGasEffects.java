package net.trentv.gasesframework.common.capability;

import net.trentv.gasesframework.api.capability.IGasEffects;

public class BaseGasEffects implements IGasEffects
{
	public float suffocation = 0;
	public float blindness = 0;
	public float slowness = 0;

	@Override
	public float getSuffocation()
	{
		return suffocation;
	}

	@Override
	public float getBlindness()
	{
		return blindness;
	}

	@Override
	public float getSlowness()
	{
		return slowness;
	}

	@Override
	public float setSuffocation(float value)
	{
		suffocation = value;
		return value;
	}

	@Override
	public float setBlindness(float value)
	{
		blindness = value;
		return value;
	}

	@Override
	public float setSlowness(float value)
	{
		slowness = value;
		return value;
	}
}
