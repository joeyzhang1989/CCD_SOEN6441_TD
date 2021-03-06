package com.soen6441.core.tower;

import java.text.DecimalFormat;
import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.ArchiveCenter;
import com.soen6441.core.IArchive;
import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.SlowEffect;
import com.soen6441.core.effect.SplashEffect;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;
import com.soen6441.core.strategy.EnableStrategy;

/**
 * This class is a specific type of tower, SplashTower.
 * A SplashTower can attack a single target with a splash effect.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */

public class SplashTower extends Tower implements EnableStrategy {
	
	/*
	 * Special effects of IceTower.
	 */
	protected AffectableValue splashDamage;
	protected AffectableValue splashRange;
	public static final String SPLASH_EFFECT = "splashEffect";
	private String effectImageName;
	
	/*
	 * Basic methods
	 */
	/**
	 * Copy properties from a Splash Tower object to another.
	 * 
	 * @param tower 
     * @see Tower#copyTo(Tower)
     */
	@Override
	public void copyTo (Tower tower) {
		super.copyTo(tower);
		
		SplashTower splashTower = (SplashTower)tower;
		splashTower.splashDamage = new AffectableValue(this.splashDamage.getOriginalValue ());
		splashTower.splashRange = new AffectableValue(this.splashRange.getOriginalValue ());
		splashTower.effectImageName = this.effectImageName;
		
	}
	
	/**
	 * Return detail information of a SplashTower object.
	 * @return String 
     */
	@Override
	public String getDetailInformation() {
		
		DecimalFormat df = new DecimalFormat("0.0");
		
		String result = super.getDetailInformation();
		result += "SplashDamage : " + df.format(this.splashDamage.getAffectedValue()) + System.getProperty("line.separator")
				 +"SplashRange : " + df.format(this.splashRange.getAffectedValue()) + "s" + System.getProperty("line.separator");
		return result;
	}
	
	public static void registeToArchiveCenter() {
		ArchiveCenter.registeClass(SplashTower.class, NameForArchiving.Class);
	}
	
	/**
	 * Inner class contains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving{
		public static final String Class = "SplashTower";
		private static final String SplashDamage = "splashDamage";
		private static final String SplashRange = "splashRange";
		private static final String EfecctImageName = "effectImageName";
	}
	/**
	 * Set value of properties of an splash tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		
		super.decode(element);
		this.setSplashDamage(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SplashDamage).getText())));
		this.setSplashRange(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.SplashRange).getText())));
		this.setEffectImageName(element.element(NameForArchiving.EfecctImageName).getText());
	}
	
	/**
	 * Encode value of properties of an splash tower object.
	 * @return Element
	 * @see Tower#encode(Element)
     * @see IArchive 
     */
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.SplashDamage).addText(String.valueOf(this.splashDamage.getOriginalValue ()));
		element.addElement(NameForArchiving.SplashRange).addText(String.valueOf(this.splashRange.getOriginalValue ()));
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
			
			SplashEffect effect = new SplashEffect(SPLASH_EFFECT);
			effect.setSplashDamage((int) this.splashDamage.getAffectedValue());
			effect.setSplashRange(this.splashRange.getAffectedValue());
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
		this.splashDamage.setAffectedValue(this.splashDamage.getOriginalValue () * enhanceRate);
		this.splashRange.setAffectedValue(this.splashRange.getOriginalValue () * enhanceRate);
	}
	
	/**
	 * This method is used to reset all effected values of this object to its original values.
	 * @see MapItem 
     */
	@Override
	protected void resetAffectableValue() {
		super.resetAffectableValue();
		this.splashDamage.setAffectedValue(this.splashDamage.getOriginalValue ());
		this.splashRange.setAffectedValue(this.splashRange.getOriginalValue ());
	}
	/*
	 * Getters and Setters
	 */
	

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

	/**
	 * Method getSplashDamage.
	 * @return AffectableValue
	 */
	public AffectableValue getSplashDamage() {
		return splashDamage;
	}

	/**
	 * Method setSplashDamage.
	 * @param splashDamage AffectableValue
	 */
	public void setSplashDamage(AffectableValue splashDamage) {
		this.splashDamage = splashDamage;
	}

	/**
	 * Method getSplashRange.
	 * @return AffectableValue
	 */
	public AffectableValue getSplashRange() {
		return splashRange;
	}

	/**
	 * Method setSplashRange.
	 * @param splashRange AffectableValue
	 */
	public void setSplashRange(AffectableValue splashRange) {
		this.splashRange = splashRange;
	}
	
}
