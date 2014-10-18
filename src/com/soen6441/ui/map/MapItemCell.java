package com.soen6441.ui.map;

import com.soen6441.core.map.MapItem;
import com.soen6441.ui.common.GridViewCell;

/**
 * This class defines a unit of the mapView.
 * 
 * @author JeanRaymondDaher 
 * @since 0.1
 * @see GridViewCell MapView
 * 
 */

public class MapItemCell extends GridViewCell{

	private MapItem item;
	
	/**
	 * Method getItem.
	 * @return MapItem
	/*
	 * Getters & Setters
	 */
	public MapItem getItem() {
		return item;
	}

	/**
	 * Method setItem.
	 * @param item MapItem
	 */
	public void setItem(MapItem item) {
		this.item = item;
	}

}
