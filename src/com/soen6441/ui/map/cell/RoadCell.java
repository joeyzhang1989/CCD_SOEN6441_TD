package com.soen6441.ui.map.cell;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.soen6441.core.map.Road;
import com.soen6441.ui.map.MapItemCell;

/**
 */
public class RoadCell extends MapItemCell{
	@Override
	public void paint(Graphics g) {
		String[] fileNames = {"road_cell_normal.png", "road_cell_start.png", "road_cell_end.png"};
		String fileName = null;
		switch (this.getRoadItem().getType()) {
		case NORMAL:
			fileName = fileNames[0];
			break;
		case START:
			fileName = fileNames[1];
			break;
		case END:
			fileName = fileNames[2];
			break;
		default:
			break;
		}
		g.drawImage(new ImageIcon("images/" + fileName).getImage(), 0, 0, null);
		super.paint(g);
	}
	
	public Road getRoadItem(){
		return (Road)this.getItem();
	}
}
