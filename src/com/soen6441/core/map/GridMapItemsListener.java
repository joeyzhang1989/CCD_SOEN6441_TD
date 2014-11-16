package com.soen6441.core.map;

import com.soen6441.core.critter.Critter;

public interface GridMapItemsListener {
	public void gridMapDidAddItem(MapItem item);
	public void gridMapDidRemoveItem(MapItem item);
	
	public void gridMapDidAddCritter(Critter critter);
	public void gridMapDidRemoveCritter(Critter critter);
}
