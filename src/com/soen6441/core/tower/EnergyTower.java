package com.soen6441.core.tower;

import java.util.List;

import org.dom4j.Element;

import com.soen6441.core.effect.MotivateEffect;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;

/**
 * This class is a specific type of tower, EnergyTower.
 * A EnergyTower does not attack any targets but enhance the power of other towers in its range.
 * 
 * @see Tower
 * 
 * @author Haiyang Sun
 * @version $Revision: 1.0 $
 */
public class EnergyTower extends Tower {
	
	/*
	 * Properties
	 */
	
	private double enhanceRate;
	private double enhanceDuration;
	private String effectImageName;
	public static final String ENHACE_EFFECT = "enhanceEffect";
	
	/*
	 * Basic methods
	 */
	
	/**
	 * Copy properties from one EnergyTower object to another.
	 * 
	 * @param tower
	 * @see Tower#copyTo(Tower)  
     */
	@Override
	public void copyTo(Tower tower){
		EnergyTower energyTower = (EnergyTower)tower;
		super.copyTo(energyTower);
		energyTower.enhanceRate = this.enhanceRate;
		energyTower.effectImageName = this.effectImageName;
	}
	
	/**
	 * Return detail information of a EnergyTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = "CDTime :" + this.getCdTime() + System.getProperty("line.separator")
					  + "Rate :" + this.enhanceRate + System.getProperty("line.separator");
		return result;
	}
	
	/**
	 * Inner class contains name strings for archiving.
	 * @author Haiyang Sun
	 *
	 */
	public class NameForArchiving{
		
		public static final String Class = "EnergyTower";
		private static final String EnhanceRate = "enhanceRate";
		private static final String EnhanceDuration = "enhanceDuration";
		private static final String EfecctImageName = "effectImageName";
	
	}
	
	/**
	 * Set value of properties of an sun tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		super.decode(element);
		this.setEnhanceRate(Double.parseDouble(element.element(NameForArchiving.EnhanceRate).getText()));
		this.setEnhanceDuration(Double.parseDouble(element.element(NameForArchiving.EnhanceDuration).getText()));
		this.setEffectImageName(element.element(NameForArchiving.EfecctImageName).getText());

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
		element.addElement(NameForArchiving.EnhanceRate).addText(String.valueOf(this.enhanceRate));
		element.addElement(NameForArchiving.EnhanceDuration).addText(String.valueOf(this.enhanceDuration));
		element.addElement(NameForArchiving.EfecctImageName).addText(this.getEffectImageName());
		
		return element;
	}
	
	/*
	 * Attack methods
	 */
	@Override
	protected void searchForTargets() {
		MapItemSelector selector = map.getItemSelector();
		List<MapItem> targets = selector
				.filterByType(Tower.class)
				.filterByCircularArea(this.getLocation(), this.getRange().getEffectedValue())
				.sortByDirectlyClosestToPoint(this.getLocation())
				.getItems();
		
		this.setTargets(targets);
	}
	
	@Override
	protected void attack() {
		for (MapItem item:this.getTargets()) {
			
			MotivateEffect effect = new MotivateEffect();
			effect.setEnhanceRate(this.enhanceRate);
			effect.setEnhanceDuration(this.enhanceDuration);
			effect.setCellImageName(this.getEffectImageName());
			
			Tower tower = (Tower)item;
			if (tower.getClass() != this.getClass()) {
				tower.addEffect(effect);
			}
					
				
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

	public double getEnhanceRate() {
		return enhanceRate;
	}

	public void setEnhanceRate(double enhanceRate) {
		this.enhanceRate = enhanceRate;
	}

	public String getEffectImageName() {
		return effectImageName;
	}

	public void setEffectImageName(String effectImageName) {
		this.effectImageName = effectImageName;
	}

	public double getEnhanceDuration() {
		return enhanceDuration;
	}

	public void setEnhanceDuration(double enhanceDuration) {
		this.enhanceDuration = enhanceDuration;
	}
}
