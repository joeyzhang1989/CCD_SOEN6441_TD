package com.soen6441.ui.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import com.soen6441.core.effect.Effect;
import com.soen6441.core.map.MapItem;
import com.soen6441.ui.parallel.ImageAssets;
import com.soen6441.ui.parallel.View;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class MapItemView extends View implements Observer{
	
	{
		this.setBackground(new Color(0x111111));
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	private MapItem item;
	
	/*
	 * Mark - Basic - Getters & Setters
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
	 * Mark - Draw - Methods
	 */
	 
	/**
	 * Method paint.
	 * @param g Graphics
	 */
	public void paint(Graphics g) {
		super.paint(g);
		MapItem item = this.getItem();
		if (item != null) {
			String cellImageName = item.getCellImageName();
			if (cellImageName != null) {
				g.drawImage(ImageAssets.imageNamed(cellImageName), 0, 0, null);
			}
		}
		paintEffects(g);
	}

	/**
	 * Method paintEffects.
	 * @param g Graphics
	 */
	private void paintEffects(Graphics g) {
		if (this.item != null) {
			for (Effect effect : item.getAllEffects()) {
				g.drawImage(ImageAssets.imageNamed(effect.getCellImageName()), 0, 0, null);
			}
		}
	}
	
	/*
	 * Mark - Observe - Methods
	 */
	 
	/**
	 * Method update.
	 * @param o Observable
	 * @param arg Object
	 * @see java.util.Observer#update(Observable, Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
