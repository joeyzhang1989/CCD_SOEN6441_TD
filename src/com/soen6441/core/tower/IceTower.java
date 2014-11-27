package com.soen6441.core.tower;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.ArchiveCenter;
import com.soen6441.core.IArchive;
import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.SlowEffect;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;
import com.soen6441.core.strategy.EnableStrategy;

/**
 * This class is a specific type of tower, IceTower.
 * A IceTower can attack a single target and reduce its running speed.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */

public class IceTower extends Tower implements EnableStrategy {
	
	/*
	 * Special effects of IceTower.
	 */
	protected AffectableValue slowRate;
	protected AffectableValue slowDuration;
	public static final String ICE_EFFECT = "iceEffect";
	private String effectImageName;
	
	/*
	 * Basic methods
	 */
	/**
	 * Copy properties from a IceTower object to another.
	 * 
	 * @param tower 
     * @see Tower#copyTo(Tower)
     */
	@Override
	public void copyTo (Tower tower) {
		super.copyTo(tower);
		
		IceTower iceTower = (IceTower)tower;
		iceTower.slowRate = new AffectableValue(this.slowRate.getOriginalValue ());
		iceTower.slowDuration = new AffectableValue(this.slowDuration.getOriginalValue ());
		iceTower.effectImageName = this.effectImageName;
		
	}
	
	/**
	 * Return detail information of a IceTower object.
	 * @return String 
     */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		result += "DcRate : " + this.slowRate.getOriginalValue () + "%" + System.getProperty("line.separator")
				 +"DcTime : " + this.slowDuration.getOriginalValue () + "s" + System.getProperty("line.separator");
		return result;
	}
	
	public static void registeToArchiveCenter() {
		ArchiveCenter.registeClass(IceTower.class, NameForArchiving.Class);
	}
	
	/**
	 * Inner class cantains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving{
		public static final String Class = "IceTower";
		private static final String SlowRate = "slowRate";
		private static final String SlowDuration = "slowDuration";
		private static final String EfecctImageName = "effectImageName";
	}
	/**
	 * Set value of properties of an ice tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		
		super.decode(element);
		this.setSlowRate(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SlowRate).getText())));
		this.setSlowDuration(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SlowDuration).getText())));
		this.setEffectImageName(element.element(NameForArchiving.EfecctImageName).getText());
	}
	
	/**
	 * Encode value of properties of an ice tower object.
	 * @return Element
	 * @see Tower#encode(Element)
     * @see IArchive 
     */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.SlowRate).addText(String.valueOf(this.slowRate.getOriginalValue ()));
		element.addElement(NameForArchiving.SlowDuration).addText(String.valueOf(this.slowDuration.getOriginalValue ()));
		element.addElement(NameForArchiving.EfecctImageName).addText(this.getEffectImageName());
		return element;
	}
	
	/*
	 * Attack methods
	 */
	
	/**
	  * This method is used to get particular targets of this tower on a map.
	  * @see Tower#searchForTargets()
	  * @see MapItemSelector
	  */
	@Override
	protected void searchForTargets() {
		MapItemSelector selector = map.getItemSelector();
		List<MapItem> targets = selector
				.filterByType(Critter.class)
				.filterByCircularArea(this.getLocation(), this.getRange().getAffectedValue())
				.sortByStrategy(this.getStrategyName(), this)
				.filterByAmount(1)
				.getItems();
		
		this.setTargets(targets);
	}
	
	/**
	 * Attack method: make damage to its target then add a SlowEffect.
	 * @see SlowEffect
	 * @see Tower#attack()
	 */
	@Override
	protected void attack() {
		for (MapItem item:this.getTargets()) {
			
			SlowEffect effect = new SlowEffect(ICE_EFFECT);
			effect.setSlowRate(this.slowRate.getAffectedValue());
			effect.setSlowDuration(this.slowDuration.getAffectedValue());
			effect.setCellImageName(this.getEffectImageName());
			
			Critter critter = (Critter)item;
			critter.damaged((int) this.getDamage().getAffectedValue());	
			critter.addEffect(effect);			
		}
	}
	
	/**
	 * This method is use to enhance properties of the tower.
	 * It is called by Motivate Effect, EnergyTower.
	 * @param enhanceRate double
	 * @see MotivateEffect 
     * @see EnergyTower
     */
	@Override
	public void reinforce(double enhanceRate) {
		super.reinforce(enhanceRate);
		this.slowDuration.setAffectedValue(this.slowDuration.getOriginalValue () * enhanceRate);
		this.slowRate.setAffectedValue(this.getSlowRate().getOriginalValue () / enhanceRate);
	}
	
	/**
	 * This method is used to reset all effected values of this object to its original values.
	 * @see MapItem 
     */
	@Override
	protected void resetAffectableValue() {
		super.resetAffectableValue();
		this.slowDuration.setAffectedValue(this.slowDuration.getOriginalValue ());
		this.slowRate.setAffectedValue(this.slowRate.getOriginalValue ());
	}
	/*
	 * Getters and Setters
	 */
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

	/**
	 * Method getEffectImageName.
	 * @return String
	 */
	public String getEffectImageName() {
		return effectImageName;
	}

	/**
	 * Method setEffectImageName.
	 * @param effectImageName String
	 */
	public void setEffectImageName(String effectImageName) {
		this.effectImageName = effectImageName;
	}
	
}
