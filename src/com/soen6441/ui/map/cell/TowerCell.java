package com.soen6441.ui.map.cell;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.soen6441.ui.map.MapItemCell;

/**
 */
public class TowerCell extends MapItemCell {
	@Override
	public void paint(Graphics g) {
		g.drawImage(new ImageIcon("images/tower_cell_1_1.png").getImage(), 0, 0, null);
		super.paint(g);
	}
}
