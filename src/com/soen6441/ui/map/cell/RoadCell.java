package com.soen6441.ui.map.cell;

import java.awt.Graphics;
import java.util.Observable;

import javax.swing.ImageIcon;

import com.soen6441.core.map.Road;
import com.soen6441.ui.map.MapItemCell;
import com.soen6441.ui.parallel.ImageAssets;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class RoadCell extends MapItemCell{
	
	/*
	 * Mark - Displaying - Methods
	 */
	
	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(ImageAssets.imageNamed(getItem().getCellImageName()), 0, 0, null);
		super.paintSelection(g);
	}
	
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
