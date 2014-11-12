package com.soen6441.ui.map.itemView;

import java.awt.Graphics;
import java.util.Observable;

import com.soen6441.core.map.Road;
import com.soen6441.ui.map.MapItemView;
import com.soen6441.ui.parallel.ImageAssets;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class RoadView extends MapItemView{
	
	/*
	 * Mark - Displaying - Methods
	 */
	
	/**
	 * Method update.
	 * @param o Observable
	 * @param arg Object
	 * @see java.util.Observer#update(Observable, Object) 
     */
	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if (arg == Road.OBSERVABLE_EVENT_PROPERTY_TYPE_DID_CHANGE){
			this.repaint();
		}
	}
	
	/*
	 * Mark - Convenience - Methods
	 */
	
	/**
	 * Method getItemRoad.	
	 * @return Road 
     */
	public Road getItemRoad(){
		return (Road)this.getItem();
	}
}
