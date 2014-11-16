package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.critter.Critter;

public class PoisonEffect extends Effect implements TimerListener{
	
	int poisonDamage;
	double poisonCdTime;
	int poisonTimes;
	
	public PoisonEffect(String type) {
		
		this.setType(type);
		
	}
	public PoisonEffect() {
		
	}
	
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(type)) {
			PoisonEffect exist = (PoisonEffect)effect;			
			if (this.getPoisonDamage() > exist.getPoisonDamage()) {
				return true;
			}
		}
		return false;
		
	}
	
	@Override
	public void start() {
		
		this.getTimer().setTimeIntervalSecond(this.poisonCdTime);
		this.getTimer().setRepeats(true);
		this.getTimer().setRepeatTimes(this.poisonTimes);
		this.getTimer().start();		
	}
	
	@Override
	public void stop() {
		
		this.getTimer().stop();
	}
	
	@Override
	public void timerTick(Timer timer) {

		Critter critter = (Critter)this.getOn();
		
		if (this.getTimer().getCurrentRepeatTimes() == this.poisonTimes) {
			
			this.getOn().removeEffect(this);
	//		critter.damaged(poisonDamage);
			
		} else {
			
//			critter.damaged(poisonDamage);
		}
		
	}
	
	

	public int getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(int poisonDamage) {
		this.poisonDamage = poisonDamage;
	}

	public double getPoisonCdTime() {
		return poisonCdTime;
	}

	public void setPoisonCdTime(double poisonCdTime) {
		this.poisonCdTime = poisonCdTime;
	}

	public int getPoisonTimes() {
		return poisonTimes;
	}

	public void setPoisonTimes(int poisonTimes) {
		this.poisonTimes = poisonTimes;
	}

	
	
}
