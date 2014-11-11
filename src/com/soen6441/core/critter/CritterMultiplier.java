package com.soen6441.core.critter;

import org.dom4j.Element;

import com.soen6441.core.IArchive;

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
	
	@Override
	public void decode(Element element) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Element encode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
