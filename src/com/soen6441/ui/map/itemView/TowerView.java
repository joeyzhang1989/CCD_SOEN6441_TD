package com.soen6441.ui.map.itemView;

import java.util.Observable;

import com.soen6441.core.tower.Tower;
import com.soen6441.ui.map.MapItemView;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class TowerView extends MapItemView {
	
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
		if (arg == Tower.OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE){
			this.repaint();
		}
	}

	/*
	 * Mark - Convenience - Methods
	 */
	
	/**
	 * Method getItemTower.	
	 * @return Tower
     */
	public Tower getItemTower(){
		return (Tower)this.getItem();
	}
}
