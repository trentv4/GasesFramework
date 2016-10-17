package net.trentv.gasesframework.common;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialGas extends Material
{
	public static final Material INSTANCE = new MaterialGas(MapColor.AIR);

	public MaterialGas(MapColor color)
	{
		super(color);
		this.setReplaceable();
		this.setNoPushMobility();
	}

	@Override
	public boolean isSolid()
	{
		return false;
	}

	@Override
	public boolean blocksMovement()
	{
		return false;
	}
}
