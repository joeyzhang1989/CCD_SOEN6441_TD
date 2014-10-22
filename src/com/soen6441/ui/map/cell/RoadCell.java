package com.soen6441.ui.map.cell;

import java.awt.Color;

import com.soen6441.ui.map.MapItemCell;

/**
 */
public class RoadCell extends MapItemCell{
	public RoadCell() {
		this.setBackground(new Color(255, 255, 0));
	}
	
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		Color color = this.isSelected() ? new Color(255, 0, 0) : new Color(255, 255, 0);
		this.setBackground(color);
	}
}
