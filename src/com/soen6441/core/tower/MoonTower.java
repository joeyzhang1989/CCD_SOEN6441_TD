package com.soen6441.core.tower;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.SlowEffect;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;

/**
 * This class is a specific type of tower, MoonTower.
 * A MoonTower is an AOE tower attacks multiple targets with deceleration effect.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */

public class MoonTower extends Tower {
	
	/*
	 * Special effects of MoonTower.
	 */
	protected AffectableValue slowRate;
	protected AffectableValue slowDuration;
	public static final String SLEEP_EFFECT = "sleepEffect";
	
	/*
	 * Basic methods
	 */
	
	/**
	 * Copy properties from a MoonTower object to another.
	 * 
	 * @param tower 
     * @see Tower#copyTo(Tower)   
     */
	@Override
	public void copyTo (Tower tower) {
		super.copyTo(tower);
		
		MoonTower moonTower = (MoonTower)tower;
		moonTower.slowRate = new AffectableValue(this.slowRate.getOriginalValue ());
		moonTower.slowDuration = new AffectableValue(this.slowDuration.getOriginalValue ());
		
	}
	
	/**
	 * Return detail information of a MoonTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		result += "DcRate : " + this.slowRate.getOriginalValue () + "%" + System.getProperty("line.separator")
				 +"DcTime : " + this.slowDuration.getOriginalValue () + "s" + System.getProperty("line.separator");
		return result;
	}

	/**
	 * Inner class cantains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 */
	public class NameForArchiving{
		public static final String Class = "MoonTower";
		private static final String SlowRate = "slowRate";
		private static final String SlowDuration = "slowDuration";
	}
	
	/**
	 * Set value of properties of an moon tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		
		super.decode(element);
		this.setSlowRate(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SlowRate).getText())));
		this.setSlowDuration(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SlowDuration).getText())));
	}
	
	/**
	 * Encode value of properties of an moon tower object.
	 * @see Tower#encode(Element)
	 * @see IArchive
	 */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.SlowRate).addText(String.valueOf(this.slowRate.getOriginalValue ()));
		element.addElement(NameForArchiving.SlowDuration).addText(String.valueOf(this.slowDuration.getOriginalValue ()));
		return element;
	}
	
	/**
	 * Method getSlowRate.
     * @return AffectableValue  
     */
	public AffectableValue getSlowRate() {
		return slowRate;
	}
	
	/**
	 * Method setSlowRate.
	 * @param slowRate AffectableValue
	 */
	public void setSlowRate(AffectableValue slowRate) {
		this.slowRate = slowRate;
	}
	
	/**
	 * Method getSlowDuration.
     * @return AffectableValue 
     */
	public AffectableValue getSlowDuration() {
		return slowDuration;
	}
	
	/**
	 * Method setSlowDuration.
	 * @param slowDuration AffectableValue
	 */
	public void setSlowDuration(AffectableValue slowDuration) {
		this.slowDuration = slowDuration;
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
			critter.addEffect(new SlowEffect(SLEEP_EFFECT, this.slowRate.getEffectedValue(), this.slowDuration.getEffectedValue()));
//			
		}
	}
	
}
