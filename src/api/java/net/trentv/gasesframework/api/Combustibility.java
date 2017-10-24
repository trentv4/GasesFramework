package net.trentv.gasesframework.api;

public class Combustibility
{
	public static final Combustibility NONE = new Combustibility(0, -1, 0.0F);
	public static final Combustibility CONTROLLABLE = new Combustibility(1, -1, 0.0F);
	public static final Combustibility FLAMMABLE = new Combustibility(2, 4, 0.0F);
	public static final Combustibility HIGHLY_FLAMMABLE = new Combustibility(0, -1, 0.0F);
	public static final Combustibility EXPLOSIVE = new Combustibility(4, -1, 1F);
	public static final Combustibility HIGHLY_EXPLOSIVE = new Combustibility(5, -1, 2f);

	public final int burnRate;
	public final int fireSpreadRate;
	public final float explosionPower;

	public Combustibility(int burnRate, int fireSpreadRate, float explosionPower)
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
