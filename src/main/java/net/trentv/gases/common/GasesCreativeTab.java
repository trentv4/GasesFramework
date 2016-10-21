package net.trentv.gases.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.trentv.gasesframework.init.GasesFrameworkObjects;

public class GasesCreativeTab extends CreativeTabs
{
	public GasesCreativeTab(String label)
	{
		super(label);
	}

	@Override
	public Item getTabIconItem()
	{
		return GasesFrameworkObjects.SMOKE.itemBlock;
	}
}
