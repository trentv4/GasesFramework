package net.trentv.gasesframework.api;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GasType
{
	private static int maxGasID = 0;

	// Mandatory fields
	public final boolean isIndustrial;
	public final int gasID;
	public final String name;
	public final int color;
	public final int opacity;
	public final Combustability combustability;
	public int density = 0;

	public Block block;
	public ItemBlock itemBlock;
	@SideOnly(Side.CLIENT)
	private GasColor gasColor;

	// Optional or circumstantial fields.
	public int dissipationRate = 0;
	public int cohesion = 16;
	public float lightLevel = 0.0F;
	public CreativeTabs creativeTab;
	
	public GasType(boolean isIndustrial, String name, int color, int opacity, int density, Combustability combustability)
	{
		this.isIndustrial = isIndustrial;
		this.gasID = maxGasID++;
		this.name = name;
		this.color = color;
		this.opacity = opacity;
		this.density = density;
		this.combustability = combustability;
	}

	public GasType setDensity(int density)
	{
		this.density = density;
		return this;
	}

	public GasType setDissipationRate(int dissipationRate)
	{
		this.dissipationRate = dissipationRate;
		return this;
	}

	public GasType setCohesion(int cohesion)
	{
		this.cohesion = cohesion;
		return this;
	}

	public GasType setLightLevel(float lightLevel)
	{
		this.lightLevel = lightLevel;
		return this;
	}

	public GasType setCreativeTab(CreativeTabs creativeTab)
	{
		this.creativeTab = creativeTab;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	public GasColor getGasColor()
	{
		if(gasColor == null) gasColor = new GasColor(this);
		return gasColor;
	}
}
