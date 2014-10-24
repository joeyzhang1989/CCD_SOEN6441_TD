package com.soen6441.ui.map.cell;

import java.awt.Graphics;
import java.util.Observable;

import javax.swing.ImageIcon;

import com.soen6441.core.tower.Tower;
import com.soen6441.ui.map.MapItemCell;

/**
 */
public class TowerCell extends MapItemCell {
	@Override
	public void paint(Graphics g) {
		g.drawImage(new ImageIcon("images/tower_cell_1_" + getTowerItem().getLevel() + ".png").getImage(), 0, 0, null);
		super.paint(g);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if (arg == Tower.OBSERVABLE_EVENT_PROPERTY_LEVEL_DID_CHANGE){
			System.out.println(getTowerItem().getLevel());
			this.repaint();
		}
	}
	
	public Tower getTowerItem(){
		return (Tower)this.getItem();
	}
}
