package net.trentv.gasesframework.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.trentv.gasesframework.init.GasesFrameworkObjects;

public class GasesFrameworkCreativeTab extends CreativeTabs
{
	public GasesFrameworkCreativeTab(String label)
	{
		super(label);
	}

	@Override
	public Item getTabIconItem()
	{
		return GasesFrameworkObjects.SMOKE.itemBlock;
	}
}
