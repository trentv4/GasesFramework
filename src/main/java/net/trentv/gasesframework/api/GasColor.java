package net.trentv.gasesframework.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GasColor implements IBlockColor, IItemColor
{
	public GasType master;
	
	public GasColor(GasType master)
	{
		this.master = master;
	}

	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex)
	{
		return master.color;
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex)
	{
		return master.color;
	}
}
