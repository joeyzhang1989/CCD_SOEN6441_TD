package com.soen6441.core.tower;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.ArchiveCenter;
import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.DotEffect;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;
import com.soen6441.core.strategy.EnableStrategy;

/**
 * This class is a specific type of tower, FireTower.
 * A FireTower is a tower with DOT burn effect on a single target.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */
public class FireTower extends Tower implements EnableStrategy{
	/*
	 * Properties
	 */
	protected AffectableValue burnDamage;
	protected AffectableValue burnCdTime;
	protected int burnTimes;
	private String effectImageName;
	public static final String BURN_EFFECT = "burnEffect";
	
	/*
	 * Basic methods
	 */
	
	/**
	 * Copy properties from one BurnTower object to another.
	 * 
	 * @param tower
	 * @see Tower#copyTo(Tower)
     */
	@Override
	public void copyTo(Tower tower){
		FireTower fireTower = (FireTower)tower;
		super.copyTo(fireTower);
		fireTower.burnDamage = new AffectableValue(this.burnDamage.getOriginalValue ());
		fireTower.burnCdTime = new AffectableValue(this.burnCdTime.getOriginalValue ());
		fireTower.burnTimes = this.burnTimes;
		fireTower.effectImageName = this.effectImageName;
	
	}
	
	/**
	 * Return detail information of a BurnTower object.
	 * @return String
     */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		result += "dotDamage : " + this.burnDamage.getOriginalValue () + System.getProperty("line.separator")
				 +"dotCD : " + this.burnCdTime.getOriginalValue () + "s" + System.getProperty("line.separator")
				 +"dotTimes :" + this.burnTimes  + System.getProperty("line.separator");
		return result;
	}
	
	public static void registeToArchiveCenter() {
		ArchiveCenter.registeClass(FireTower.class, NameForArchiving.Class);
	}
	
	/**
	 * Inner class contains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving{
		public static final String Class = "FireTower";
		private static final String BurnDamage = "burnDamage";
		private static final String BurnCdTime = "burnCdTime";
		private static final String BurnTimes = "burnTimes";
		private static final String EfecctImageName = "effectImageName";
	}
	
	/**
	 * Set value of properties of a fire tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		super.decode(element);
		this.setBurnDamage(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.BurnDamage).getText())));
		this.setBurnCdTime(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.BurnCdTime).getText())));
		this.setBurnTimes(Integer.parseInt(element.element(NameForArchiving.BurnTimes).getText()));
		this.setEffectImageName(element.element(NameForArchiving.EfecctImageName).getText());
	}
	
	/**
	 * Encode value of properties of a fire tower object.
	 * @see Tower#encode()
	 * @see IArchive
	 */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.BurnDamage).addText(String.valueOf(this.burnDamage.getOriginalValue ()));
		element.addElement(NameForArchiving.BurnCdTime).addText(String.valueOf(this.burnCdTime.getOriginalValue ()));
		element.addElement(NameForArchiving.BurnTimes).addText(String.valueOf(this.getBurnTimes()));
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
	 * Attack method: make damage to its target then add a DOT BurnEffect.
	 * @see BurnEffect
	 * @see Tower#attack()
	 */
	@Override
	protected void attack() {
		for (MapItem item:this.getTargets()) {
			Critter critter = (Critter)item;
			critter.damaged((int) this.getDamage().getAffectedValue());
			
			DotEffect effect = new DotEffect(BURN_EFFECT);
			effect.setDotDamage((int) this.getBurnDamage().getAffectedValue());
			effect.setDotTimes(this.getBurnTimes());
			effect.setDotCdTime(this.getBurnCdTime().getAffectedValue());
			effect.setCellImageName(this.effectImageName);
			
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
	public void reinforce(double enhanceRate) {
		super.reinforce(enhanceRate);
		this.burnCdTime.setAffectedValue(this.burnCdTime.getOriginalValue () / enhanceRate);
		this.burnDamage.setAffectedValue(this.burnDamage.getOriginalValue () * enhanceRate);
	}
	
	/**
	 * This method is used to reset all effected values of this object to its original values.
	 * @see MapItem
     */
	@Override
	protected void resetAffectableValue() {
		super.resetAffectableValue();
		this.burnCdTime.setAffectedValue(this.burnCdTime.getOriginalValue ());
		this.burnDamage.setAffectedValue(this.burnDamage.getOriginalValue ());
	}	
	
	/*
	 * Getters and Setters
	 */
	
	public AffectableValue getBurnDamage() {
		return burnDamage;
	}

	public void setBurnDamage(AffectableValue burnDamage) {
		this.burnDamage = burnDamage;
	}

	public AffectableValue getBurnCdTime() {
		return burnCdTime;
	}

	public void setBurnCdTime(AffectableValue burnCdTime) {
		this.burnCdTime = burnCdTime;
	}

	public int getBurnTimes() {
		return burnTimes;
	}

	public void setBurnTimes(int burnTimes) {
		this.burnTimes = burnTimes;
	}

	public String getEffectImageName() {
		return effectImageName;
	}

	public void setEffectImageName(String effectImageName) {
		this.effectImageName = effectImageName;
	}	

}
