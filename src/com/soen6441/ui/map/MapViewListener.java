package com.soen6441.ui.map;

import com.soen6441.core.map.MapItem;

/**
 * This interface responds to commands , and selections on the map.
 * 
 * @author JeanRaymondDaher
 *
 */

public interface MapViewListener {

	/**
	 * Method select.
	 * @param item MapItem
	 */
	public void select(MapItem item);
	
}
 