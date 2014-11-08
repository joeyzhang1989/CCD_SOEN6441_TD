package com.soen6441.core.tower;

import org.dom4j.Element;

import com.soen6441.core.effect.AffectableValue;

public class PoisonTower extends Tower {
	
	protected AffectableValue poisonDamage;
	protected AffectableValue poisonCdTime;
	protected int poisonTimes;
	
	/**
	 * Copy properties from one PoisonTower object to another.
	 * 
	 * @param tower
	 * @see Tower#copyTo(Tower)  
     */
	@Override
	public void copyTo(Tower tower){
		PoisonTower PoisonTower = (PoisonTower)tower;
		super.copyTo(PoisonTower);
	}
	
	/**
	 * Return detail information of a PoisonTower object.
	 * @return String
	 */
	@Override
	public String getDetailInformation() {
		String result = super.getDetailInformation();
		return result;
	}
	
	public class NameForArchiving{
		public static final String Class = "PoisonTower";
		private static final String PoisonDamage = "poisonDamage";
		private static final String PoisonCdTime = "poisonCdTime";
		private static final String PoisonTimes = "poisonTimes";
	}
	
	/**
	 * Set value of properties of an bottle tower object.
	 * @see Tower#decode(Element)
	 * @see IArchive
	 */
	@Override
	public void decode(Element element) {
		super.decode(element);
		this.setPoisonDamage(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.PoisonDamage).getText())));
		this.setPoisonCdTime(new AffectableValue(Double.parseDouble(element.element(NameForArchiving.PoisonCdTime).getText())));
		this.setPoisonTimes(Integer.parseInt(element.element(NameForArchiving.PoisonTimes).getText()));
	}
	
	@Override
	public Element encode() {
		Element element = super.encode();
		element.setName(NameForArchiving.Class);
		element.addElement(NameForArchiving.PoisonDamage).addText(String.valueOf(this.poisonDamage.getOriginalValue ()));
		element.addElement(NameForArchiving.PoisonCdTime).addText(String.valueOf(this.poisonCdTime.getOriginalValue ()));
		element.addElement(NameForArchiving.PoisonTimes).addText(String.valueOf(this.getPoisonTimes()));
		return element;
	}

	public AffectableValue getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(AffectableValue poisonDamage) {
		this.poisonDamage = poisonDamage;
	}

	public AffectableValue getPoisonCdTime() {
		return poisonCdTime;
	}

	public void setPoisonCdTime(AffectableValue poisonCdTime) {
		this.poisonCdTime = poisonCdTime;
	}

	public int getPoisonTimes() {
		return poisonTimes;
	}

	public void setPoisonTimes(int poisonTimes) {
		this.poisonTimes = poisonTimes;
	}

	

}
