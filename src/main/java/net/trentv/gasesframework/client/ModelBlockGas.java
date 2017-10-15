package net.trentv.gasesframework.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import java.util.function.Function;

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
import net.trentv.gasesframework.api.GasType;

public class ModelBlockGas implements IModel
{
	private FaceBakery bakery = new FaceBakery();
	private GasType type;
	private final int quantity;
	private ResourceLocation texture;

	public ModelBlockGas(int valueOf, GasType gasType)
	{
		this.quantity = valueOf;
		this.type = gasType;

		if (type.texture == null)
		{
			texture = new ResourceLocation(GasesFramework.MODID, "block/gas_" + type.opacity);
		}
		else
		{
			texture = type.texture;
		}
	}

	@Override
	public Collection<ResourceLocation> getDependencies()
	{
		return new ArrayList<ResourceLocation>();
	}

	@Override
	public Collection<ResourceLocation> getTextures()
	{
		return Arrays.asList(texture);
	}

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
	{
		// facings in D-U-N-S-W-E order
		int ypos = 8 - quantity / 2;
		BakedQuad faceDown = getQuad(new Vector3f(0, ypos, 0), new Vector3f(16, ypos, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.DOWN, bakedTextureGetter, quantity);
		BakedQuad faceUp = getQuad(new Vector3f(0, ypos, 0), new Vector3f(16, ypos + quantity, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.UP, bakedTextureGetter, quantity);
		BakedQuad faceNorth = getQuad(new Vector3f(0, ypos, 0), new Vector3f(16, ypos + quantity, 16), new BlockFaceUV(new float[] { 0, 0, 16, quantity }, 0), EnumFacing.NORTH, bakedTextureGetter, quantity);
		BakedQuad faceSouth = getQuad(new Vector3f(0, ypos, 0), new Vector3f(16, ypos + quantity, 16), new BlockFaceUV(new float[] { 0, 0, 16, quantity }, 0), EnumFacing.SOUTH, bakedTextureGetter, quantity);
		BakedQuad faceWest = getQuad(new Vector3f(0, ypos, 0), new Vector3f(16, ypos + quantity, 16), new BlockFaceUV(new float[] { 0, 0, 16, quantity }, 0), EnumFacing.WEST, bakedTextureGetter, quantity);
		BakedQuad faceEast = getQuad(new Vector3f(0, ypos, 0), new Vector3f(16, ypos + quantity, 16), new BlockFaceUV(new float[] { 0, 0, 16, quantity }, 0), EnumFacing.EAST, bakedTextureGetter, quantity);
		List<BakedQuad> allQuads = Arrays.asList(faceDown, faceUp, faceNorth, faceSouth, faceWest, faceEast);

		HashMap<EnumFacing, List<BakedQuad>> faceQuads = new HashMap<EnumFacing, List<BakedQuad>>();
		for (int i = 0; i < allQuads.size(); i++)
		{
			BakedQuad a = allQuads.get(i);
			faceQuads.put(a.getFace(), Arrays.asList(a));
		}

		SimpleBakedModel newModel = new SimpleBakedModel(allQuads, faceQuads, true, true, bakedTextureGetter.apply(texture), ItemCameraTransforms.DEFAULT, ItemOverrideList.NONE);
		return newModel;
	}

	private BakedQuad getQuad(Vector3f from, Vector3f to, BlockFaceUV uv, EnumFacing direction, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, int quantity)
	{
		return bakery.makeBakedQuad(from, to, new BlockPartFace(null, 0, texture.toString(), uv), bakedTextureGetter.apply(texture), direction, ModelRotation.X0_Y0, null, true, true);
	}

	@Override
	public IModelState getDefaultState()
	{
		return null;
	}
}
