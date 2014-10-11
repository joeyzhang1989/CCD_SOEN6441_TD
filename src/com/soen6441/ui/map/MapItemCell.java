package com.soen6441.ui.map;

import com.soen6441.core.map.MapItem;
import com.soen6441.ui.common.GridViewCell;

/**
 * This class defines ?
 * 
 * @author JeanRaymondDaher 
 * 
 * @see GridViewCell
 * 
 */

public class MapItemCell extends GridViewCell{

	private MapItem item;
	
	public MapItem getItem() {
		return item;
	}

	public void setItem(MapItem item) {
		this.item = item;
	}

}