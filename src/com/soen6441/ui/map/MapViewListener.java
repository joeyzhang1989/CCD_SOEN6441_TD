package com.soen6441.ui.map;

import com.soen6441.logic.map.MapItem;

/**
 * This interface responds to commands , and selections on the map.
 * 
 * @author JeanRaymondDaher
 * @version 1.0
 *
 */

public interface MapViewListener {

	public void select(MapItem item);
	
}
 