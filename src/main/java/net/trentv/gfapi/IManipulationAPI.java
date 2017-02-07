package net.trentv.gfapi;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IManipulationAPI
{
	/* Getting information about gases in the world */
	
	/**
	 * @return The GasType at pos.
	 */
	public GasType getGasType(BlockPos pos, World access);
	
	/**
	 * @return The level of any gas from 0-15 at pos. 
	 */
	public int getGasLevel(BlockPos pos, World access);
	
	/**
	 * Verifies that a GasType may be legally placed at pos.
	 * @param currentGas Optionally null, returns true if found gas is the same type.
	 * @return
	 */
	public boolean canPlaceGas(BlockPos pos, World access, @Nullable GasType currentGas);

	/* Manipulating gases in the world (e.g. placing or removing) */
	
	/**
	 * Sets the current gas level at pos. This is destructive, and may result in loss of
	 * gas or blocks unintentionally. Be careful.
	 */
	public void setGasLevel(BlockPos pos, World access, GasType gas, int level);
	
	/**
	 * Removes level of gas from pos.
	 */
	public void removeGasLevel(BlockPos pos, World access, int level);
	
	/**
	 * Adds the gas level to the current block. Returns the excess level if it cannot be
	 * placed or goes over the 16 limit.
	 */
	public int addGasLevel(BlockPos pos, World access, GasType gas, int level);
}
