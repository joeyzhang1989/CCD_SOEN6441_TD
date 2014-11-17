package com.soen6441.core.map;

import com.soen6441.core.critter.Critter;

/**
 * @author Zhe Zhao
 */
public interface GridMapItemsListener {
	/**
	 * Method gridMapDidAddItem.
	 * @param item MapItem
	 */
	public void gridMapDidAddItem(MapItem item);
	/**
	 * Method gridMapDidRemoveItem.
	 * @param item MapItem
	 */
	public void gridMapDidRemoveItem(MapItem item);
	
	/**
	 * Method gridMapDidAddCritter.
	 * @param critter Critter
	 */
	public void gridMapDidAddCritter(Critter critter);
	/**
	 * Method gridMapDidRemoveCritter.
	 * @param critter Critter
	 */
	public void gridMapDidRemoveCritter(Critter critter);
	
}
