package net.trentv.gasesframework.capability;

public class BaseGasEffects implements IGasEffects
{
	public int suffocation = 0;
	public int blindness = 0;
	public int slowness = 0;

	@Override
	public int getSuffocation()
	{
		return suffocation;
	}

	@Override
	public int getBlindness()
	{
		return blindness;
	}

	@Override
	public int getSlowness()
	{
		return slowness;
	}

	@Override
	public int setSuffocation(int value)
	{
		suffocation = value;
		return value;
	}

	@Override
	public int setBlindness(int value)
	{
		blindness = value;
		return value;
	}

	@Override
	public int setSlowness(int value)
	{
		slowness = value;
		return value;
	}
}
