package com.soen6441.ui.map.cell;

import java.awt.Graphics;
import java.util.Observable;

import javax.swing.ImageIcon;

import com.soen6441.core.map.Road;
import com.soen6441.ui.map.MapItemCell;

/**
 * @author Zhe Zhao
 */
public class RoadCell extends MapItemCell{
	
	/*
	 * Mark - Displaying - Methods
	 */
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(new ImageIcon("images/" + getItem().getCellImageName()).getImage(), 0, 0, null);
		super.paint(g);
	}
	
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
	
	public Road getItemRoad(){
		return (Road)this.getItem();
	}
}
