package net.trentv.gasesframework.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import com.google.common.base.Function;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.trentv.gasesframework.GasesFramework;

public class ModelBlockGas implements IModel
{
	private FaceBakery bakery = new FaceBakery();
	private ResourceLocation texture = new ResourceLocation(GasesFramework.MODID, "block/gas_default");
	private final int quantity;
	
	public ModelBlockGas(int valueOf)
	{
		this.quantity = valueOf;
	}

	@Override
	public Collection<ResourceLocation> getDependencies()
	{
		return new ArrayList<ResourceLocation>();
	}

	@Override
	public Collection<ResourceLocation> getTextures()
	{
		ArrayList<ResourceLocation> a = new ArrayList<ResourceLocation>();
		a.add(texture);
		return a;
	}

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
	{
		//facings in D-U-N-S-W-E order 
		int ypos = 8 - quantity/2;
		List<BakedQuad> allQuads = Arrays.asList(
				getQuad(new Vector3f(0,ypos,0), new Vector3f(16,ypos,16), EnumFacing.DOWN, bakedTextureGetter, quantity), //DOWN
				getQuad(new Vector3f(0,ypos,0), new Vector3f(16,ypos+quantity,16), EnumFacing.UP, bakedTextureGetter, quantity), //UP
				getQuad(new Vector3f(0,ypos,0), new Vector3f(16,ypos+quantity,16), EnumFacing.NORTH, bakedTextureGetter, quantity), //NORTH
				getQuad(new Vector3f(0,ypos,0), new Vector3f(16,ypos+quantity,16), EnumFacing.SOUTH, bakedTextureGetter, quantity), //SOUTH
				getQuad(new Vector3f(0,ypos,0), new Vector3f(16,ypos+quantity,16), EnumFacing.WEST, bakedTextureGetter, quantity), //WEST
				getQuad(new Vector3f(0,ypos,0), new Vector3f(16,ypos+quantity,16), EnumFacing.EAST, bakedTextureGetter, quantity)  //EAST
				);
		HashMap<EnumFacing, List<BakedQuad>> faceQuads = new HashMap<EnumFacing, List<BakedQuad>>();
		for(int i = 0; i < EnumFacing.values().length; i++)
		{
			faceQuads.put(EnumFacing.VALUES[i], Arrays.asList(allQuads.get(i)));
		}
		SimpleBakedModel newModel = new SimpleBakedModel(allQuads, faceQuads, true, true, bakedTextureGetter.apply(texture), ItemCameraTransforms.DEFAULT, ItemOverrideList.NONE);
		return newModel;
	}

	private BakedQuad getQuad(Vector3f from, Vector3f to, EnumFacing direction, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, int quantity)
	{
		return bakery.makeBakedQuad(from, to, new BlockPartFace(null, 0, texture.toString(), new BlockFaceUV(new float[]{0,0,16,quantity}, 0)),
				bakedTextureGetter.apply(texture), direction, ModelRotation.X0_Y0, null, true, true);
	}
	
	@Override
	public IModelState getDefaultState()
	{
		return null;
	}
}
