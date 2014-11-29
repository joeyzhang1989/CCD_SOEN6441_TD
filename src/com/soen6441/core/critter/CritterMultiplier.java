package com.soen6441.core.critter;

import org.dom4j.Element;

import com.soen6441.core.IArchive;

/**
 * @author Zhe Zhao
 */
public class CritterMultiplier implements IArchive{
	
	/*
	 * Mark - Basic - Properties
	 */

	private String critterName;
	private double hpMultiplier;
	private double speedMultiplier;
	private double rewardMultiplier;
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	 
	/**
	 * Method getRewardMultiplier.
	 * @return double
	 */
	public double getRewardMultiplier() {
		return rewardMultiplier;
	}
	/**
	 * Method setRewardMultiplier.
	 * @param rewardMultiplier double
	 */
	public void setRewardMultiplier(double rewardMultiplier) {
		this.rewardMultiplier = rewardMultiplier;
	}
	/**
	 * Method getCritterName.
	 * @return String
	 */
	public String getCritterName() {
		return critterName;
	}
	/**
	 * Method setCritterName.
	 * @param critterName String
	 */
	public void setCritterName(String critterName) {
		this.critterName = critterName;
	}
	/**
	 * Method getHpMultiplier.
	 * @return double
	 */
	public double getHpMultiplier() {
		return hpMultiplier;
	}
	/**
	 * Method setHpMultiplier.
	 * @param hpMultiplier double
	 */
	public void setHpMultiplier(double hpMultiplier) {
		this.hpMultiplier = hpMultiplier;
	}
	/**
	 * Method getSpeedMultiplier.
	 * @return double
	 */
	public double getSpeedMultiplier() {
		return speedMultiplier;
	}
	/**
	 * Method setSpeedMultiplier.
	 * @param speedMultiplier double
	 */
	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
	
	/*
	 * Mark - Archive - Methods
	 */
	 
	/**
     * @author Zhe Zhao
	 */
	public class NameForArchiving{
		public static final String Class = "CritterMultiplier";
		private static final String SpeedMultiplier="speedMultiplier";
	//	private static final String RewardMultiplier="rewardMultiplier";
		private static final String HpMultiplier="hpMultiplier";
		private static final String Name="critterName";
	}
	
	
	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		this.setSpeedMultiplier(Double.parseDouble(element.element(NameForArchiving.SpeedMultiplier).getText()));
	//	this.setRewardMultiplier(Double.parseDouble(element.element(NameForArchiving.RewardMultiplier).getText()));
		this.setHpMultiplier(Double.parseDouble(element.element(NameForArchiving.HpMultiplier).getText()));
		this.setCritterName(element.element(NameForArchiving.Name).getText());
		this.setRewardMultiplier(1);
	}
	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		
		return null;
	}
	
	
}
