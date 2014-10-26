package com.soen6441.ui.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import com.soen6441.core.map.MapItem;
import com.soen6441.ui.common.GridViewCell;

/**
 * This class defines a unit of the mapView, our model where our grid resigns.
 * 
 * @see GridViewCell MapView
 * 
 * @author JeanRaymondDaher 
 *
 * @version $Revision: 1.0 $
 */

public class MapItemCell extends GridViewCell implements Observer{

	/*
	 * Mark - Basic - Properties
	 */
	private MapItem item;
	
	/*
	 * Getters & Setters
	 */
	
	/**
	 * Method getItem.
     * @return MapItem	
     */
	public MapItem getItem() {
		return item;
	}

	/**
	 * Method setItem.
	 * @param item MapItem
	 */
	public void setItem(MapItem item) {
		if (this.item != null) {
			this.item.deleteObserver(this);
		}
		this.item = item;
		if (this.item != null) {
			this.item.addObserver(this);
		}
	}
	
	/*
	 * Mark - Methods - Observer 
	 */
	
	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0xFFFFFF));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	protected void paintSelection(Graphics g) {
		g.setColor(new Color(0xF8F8F8));
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		if (this.isSelected()) {
			g.setColor(new Color(0xAAAAAA));
			g.drawRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
		}
	}
	
	/**
	 * Method setSelected.
	 * @param selected boolean
	 */
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		this.repaint();
	}
	
	/**
	 * Method update.
	 * @param o Observable
	 * @param arg Object
	 * @see java.util.Observer#update(Observable, Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
	}
}
