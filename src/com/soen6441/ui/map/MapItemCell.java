package com.soen6441.ui.map;

import java.awt.Color;
import java.awt.Graphics;

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

public class MapItemCell extends GridViewCell{

	/*
	 * Mark - Paint - Methods
	 */
	
	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0xFFFFFF));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
		paintSelection(g);
	}
	
	/**
	 * Method paintSelection.
	 * @param g Graphics
	 */
	protected void paintSelection(Graphics g) {
		g.setColor(new Color(0xF8F8F8));
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		if (this.isSelected()) {
			g.setColor(new Color(0xAAAAAA));
			g.drawRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
		}
	}
}
