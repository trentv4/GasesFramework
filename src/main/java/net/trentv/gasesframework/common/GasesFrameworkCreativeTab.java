package net.trentv.gasesframework.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GasesFrameworkCreativeTab extends CreativeTabs
{
	public GasesFrameworkCreativeTab(String label)
	{
		super(label);
	}

	@Override
	public Item getTabIconItem()
	{
		return Items.ARROW;
	}
}
