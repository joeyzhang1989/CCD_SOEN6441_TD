package com.soen6441.core.critter;

import com.soen6441.core.effect.AffectableValue;

public class Critter {

	private int totalHp;
	private int hp;
	private AffectableValue speed;
	
	private int reward;
	private int stealAmount;
	
	public Critter()
	{
		new Critter();
	}

	public int getTotalHp() {
		return totalHp;
	}

	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public AffectableValue getSpeed() {
		return speed;
	}

	public void setSpeed(AffectableValue speed) {
		this.speed = speed;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getStealAmount() {
		return stealAmount;
	}

	public void setStealAmount(int stealAmount) {
		this.stealAmount = stealAmount;
	}
	
	
}
