package net.trentv.gasesframework.api;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.reaction.block.IBlockReaction;
import net.trentv.gasesframework.api.reaction.entity.IEntityReaction;
import net.trentv.gasesframework.api.reaction.gas.IGasReaction;

public class GasType
{
	private static int maxGasID = 0;

	// Mandatory fields
	public final int gasID;
	public final String name;
	public final int color;
	public final int opacity;
	public final Combustibility combustability;
	public int density = 0;

	public Block block;
	public ItemBlock itemBlock;
	@SideOnly(Side.CLIENT)
	private GasColor gasColor;

	// Optional or circumstantial fields.
	public int dissipationRate = 0;
	public int dissipationAmount = 0;
	public int cohesion = 16;
	public float lightLevel = 0.0F;
	public CreativeTabs creativeTab;
	public ResourceLocation texture = new ResourceLocation(GasesFramework.MODID, "block/gas");
	public boolean tintindex = true;

	public ArrayList<IEntityReaction> entityReactions = new ArrayList<IEntityReaction>();
	public Map<GasType, Set<IGasReaction>> gasReactions = new IdentityHashMap<GasType, Set<IGasReaction>>();
	public Map<Block, Set<IBlockReaction>> blockReactions = new IdentityHashMap<Block, Set<IBlockReaction>>();

	public GasType(String name, int color, int opacity, int density, Combustibility combustability)
	{
		this.gasID = maxGasID++;
		this.name = name;
		this.color = color;
		this.opacity = opacity;
		this.density = density;
		this.combustability = combustability;
	}
	
	public GasType setTexture(ResourceLocation texture, boolean tintindex)
	{
		this.texture = texture;
		this.tintindex = tintindex;
		return this;
	}

	public GasType setDensity(int density)
	{
		this.density = density;
		return this;
	}

	public GasType setDissipation(int dissipationRate, int dissipationAmount)
	{
		this.dissipationRate = dissipationRate;
		this.dissipationAmount = dissipationAmount;
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
		if (gasColor == null)
			gasColor = new GasColor(this);
		return gasColor;
	}

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

	public GasType registerEntityReaction(IEntityReaction a)
	{
		entityReactions.add(a);
		return this;
	}

	public IEntityReaction[] getEntityReactions()
	{
		return entityReactions.toArray(new IEntityReaction[entityReactions.size()]);
	}

	public IGasReaction[] getGasReaction(GasType in)
	{
		Set<IGasReaction> set = gasReactions.get(in);
		return set.toArray(new IGasReaction[set.size()]);
	}

	public IBlockReaction[] getBlockReaction(Block in)
	{
		Set<IBlockReaction> set = blockReactions.get(in);
		return set.toArray(new IBlockReaction[set.size()]);
	}

	// Return false if you want to stop the rest of the tick processing.
	// Be sure to manually reschedule ticks 
	public boolean preTick(World world, IBlockState state, BlockPos pos)
	{
		return true;
	}

	public void postTick(World world, IBlockState state, BlockPos pos)
	{

	}
	
	public boolean requiresNewTick(World world, IBlockState state, BlockPos pos)
	{
		return true;
	}


	public boolean ignite(World world, IBlockState state, BlockPos pos)
	{
		return true;
	}
}
