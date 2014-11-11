package com.soen6441.core.critter;

import org.dom4j.Element;

import com.soen6441.core.IArchive;
import com.soen6441.core.critter.Critter.NameForArchiving;

public class CritterMultiplier implements IArchive{

	private String critterName;
	private double hpMultiplier;
	private double speedMultiplier;
	private double rewardMultiplier;
	
	
	public double getRewardMultiplier() {
		return rewardMultiplier;
	}
	public void setRewardMultiplier(double rewardMultiplier) {
		this.rewardMultiplier = rewardMultiplier;
	}
	public String getCritterName() {
		return critterName;
	}
	public void setCritterName(String critterName) {
		this.critterName = critterName;
	}
	public double getHpMultiplier() {
		return hpMultiplier;
	}
	public void setHpMultiplier(double hpMultiplier) {
		this.hpMultiplier = hpMultiplier;
	}
	public double getSpeedMultiplier() {
		return speedMultiplier;
	}
	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
	
	public class NameForArchiving{
		public static final String Class = "CritterMultiplier";
		private static final String SpeedMultiplier="speedMultiplier";
	//	private static final String RewardMultiplier="rewardMultiplier";
		private static final String HpMultiplier="hpMultiplier";
		private static final String Name="critterName";
	}
	
	
	@Override
	public void decode(Element element) {
		this.setSpeedMultiplier(Double.parseDouble(element.element(NameForArchiving.SpeedMultiplier).getText()));
	//	this.setRewardMultiplier(Double.parseDouble(element.element(NameForArchiving.RewardMultiplier).getText()));
		this.setHpMultiplier(Double.parseDouble(element.element(NameForArchiving.HpMultiplier).getText()));
		this.setCritterName(element.element(NameForArchiving.Name).getText());
		this.setRewardMultiplier(1);
	}
	@Override
	public Element encode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
