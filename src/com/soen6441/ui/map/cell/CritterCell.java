package com.soen6441.ui.map.cell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.tower.Tower;
import com.soen6441.ui.map.MapItemCell;
import com.soen6441.ui.parallel.ImageAssets;

public class CritterCell extends MapItemCell {

	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(ImageAssets.imageNamed(getItem().getCellImageName()), 0, 0, null);
		paintHpBar(g);
		super.paintSelection(g);
	}
	
	private void paintHpBar(Graphics g) {
		int width = 32;
		int height = 4;
		int x = 4;
		int y = 2;
		
		Color colorTotal = new Color(0xFF0000);
		Color colorRemain = new Color(0x00CC32);
		
		g.setColor(colorTotal);
		g.drawRect(x, y, width, height);
		
		Critter critter = getItemCritter();
		double ratio = critter.getHp() * 1.0 / critter.getTotalHp();
		g.setColor(colorRemain);
		g.drawRect(x, y, (int)(width * ratio), height);
		
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
		if (arg == Critter.OBSERVABLE_EVENT_PROPERTY_HP_DID_CHANGE){
			this.repaint();
		}
	}

	
	
	public Critter getItemCritter(){
		return (Critter)this.getItem();
	}
}
