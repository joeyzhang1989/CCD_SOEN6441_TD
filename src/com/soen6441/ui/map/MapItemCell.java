package com.soen6441.ui.map;

import java.awt.Color;
import java.awt.Graphics;

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
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0xF8F8F8));
		g.drawRect(0, 0, 40, 40);
		if (this.isSelected()) {
			g.setColor(new Color(0xAAAAAA));
			g.drawRect(1, 1, 38, 38);
		}
	}
	
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		this.repaint();
	}

}
