package com.soen6441.ui.map.itemView;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import com.soen6441.core.critter.Critter;
import com.soen6441.ui.map.MapItemView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.ImageAssets;

/**
 * @author Zhe Zhao
 */
public class CritterView extends MapItemView {
	
	public CritterView() {
		this.setBackground(new Color(0, 0, 0, 0));
	}

	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintHpBar(g);
	}
	
	/**
	 * Method paintHpBar.
	 * @param g Graphics
	 */
	private void paintHpBar(Graphics g) {
		int width = 32;
		int height = 4;
		int x = 4;
		int y = 2;
		
		Color colorTotal = new Color(0xFF0000);
		Color colorRemain = new Color(0x00CC32);
		
		g.setColor(colorTotal);
		g.fillRect(x, y, width, height);
		
		Critter critter = getItemCritter();
		double ratio = critter.getHp() * 1.0 / critter.getTotalHp();
		g.setColor(colorRemain);
		g.fillRect(x, y, (int)(width * ratio), height);
		
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
//			this.repaint();
		} else if (arg == Critter.OBSERVABLE_EVENT_PROPERTY_LOCATION_DID_CHANGE) {
			this.setLocation(MapView.mapPointToSwingPoint(getItem().getLocation()));
//			this.repaint();
		}
	}

	
	/**
	 * Method getItemCritter.
	 * @return Critter
	 */
	public Critter getItemCritter(){
		return (Critter)this.getItem();
	}
}
