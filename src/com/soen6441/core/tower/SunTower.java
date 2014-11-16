package com.soen6441.core.tower;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;

/**
 * This class is a specific type of tower, SunTower.
 * A SunTower is an AOE tower attacks all Targets within its range.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */
public class SunTower extends Tower {
	
	/*
	 * Basic methods
	 */
	
	/**
	 * Copy properties from one SunTower object to another.
	 * 
	 * @param tower
	 * @see Tower#copyTo(Tower)  
     */
	@Override
	public void copyTo(Tower tower){
		SunTower sunTower = (SunTower)tower;
		super.copyTo(sunTower);
	}
	
	/**
	 * Return detail information of a SunTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		return result;
	}
	
	/**
	 * Inner class contains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 */
	public class NameForArchiving{
		
		public static final String Class = "SunTower";
	
	}
	
	/**
	 * Set value of properties of an sun tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		super.decode(element);
	}
	
	/**
	 * Encode value of properties of an sun tower object.
	 * @see Tower#encode(Element)
	 * @see IArchive
	 */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		return element;
	}
	
	/*
	 * Attack methods
	 */
	@Override
	protected void searchForTargets() {
		MapItemSelector selector = map.getItemSelector();
		List<MapItem> targets = selector
				.filterByType(Critter.class)
				.filterByCircularArea(this.getLocation(), this.getRange().getEffectedValue())
				.sortByDirectlyClosestToPoint(this.getLocation())
				.getItems();
		
		this.setTargets(targets);
	}
	
	@Override
	protected void attack() {
		for (MapItem item:this.getTargets()) {
			Critter critter = (Critter)item;
			critter.damaged((int) this.getDamage().getEffectedValue());	
		}
	}
	@Override
	public void reinforce(double enhanceRate) {
		super.reinforce(enhanceRate);
	}
	

	@Override
	protected void resetAffectableValue() {
		super.resetAffectableValue();
	}
}
