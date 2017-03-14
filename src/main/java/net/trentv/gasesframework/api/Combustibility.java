package net.trentv.gasesframework.api;

public enum Combustibility
{
	NONE(0, -1, 0.0F), CONTROLLABLE(1, -1, 0.0F), FLAMMABLE(2, 4, 0.0F), HIGHLY_FLAMMABLE(3, 2, 0.0F), EXPLOSIVE(4, -1, 1F), HIGHLY_EXPLOSIVE(5, -1, 2F);

	public final int burnRate;
	public final int fireSpreadRate;
	public final float explosionPower;

	Combustibility(int burnRate, int fireSpreadRate, float explosionPower)
	{
		this.burnRate = burnRate;
		this.fireSpreadRate = fireSpreadRate;
		this.explosionPower = explosionPower;
	}
	
	public boolean isFlammable()
	{
		return burnRate > 0;
	}
}
