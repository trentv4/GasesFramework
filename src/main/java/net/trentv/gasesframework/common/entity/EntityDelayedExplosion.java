package net.trentv.gasesframework.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDelayedExplosion extends Entity
{
	private int ticksToExplode;
	private float explosionPower;
	private boolean isFlaming;
	private boolean isSmoking;

	public EntityDelayedExplosion(World world)
	{
		super(world);
	}

	public EntityDelayedExplosion(World world, int ticksToExplode, float explosionPower, boolean isFlaming, boolean isSmoking)
	{
		super(world);
		this.ticksToExplode = ticksToExplode;
		this.explosionPower = explosionPower;
		this.isFlaming = isFlaming;
		this.isSmoking = isSmoking;
	}

	@Override
	public void onUpdate()
	{
		if (this.ticksToExplode-- <= 0)
		{
			this.setDead();

			if (!this.worldObj.isRemote)
			{
				this.explode();
			}
		}
	}

	protected void explode()
	{
		this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, this.explosionPower, this.isFlaming, this.isSmoking);
	}

	@Override
	protected void entityInit()
	{

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		ticksToExplode = tagCompound.getInteger("ticksToExplode");
		explosionPower = tagCompound.getFloat("explosionPower");
		isFlaming = tagCompound.getBoolean("isFlaming");
		isSmoking = tagCompound.getBoolean("isSmoking");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setInteger("ticksToExplode", ticksToExplode);
		tagCompound.setFloat("explosionPower", explosionPower);
		tagCompound.setBoolean("isFlaming", isFlaming);
		tagCompound.setBoolean("isSmoking", isSmoking);
	}
}
