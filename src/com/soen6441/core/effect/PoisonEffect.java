package com.soen6441.core.effect;

public class PoisonEffect extends Effect{
	
	int poisonDamage;
	double poisonCdTime;
	int poisonTimes;
	
	public static final String EFFECT_TYPE = "poisonEffect";
	
	public PoisonEffect () {
		this.type = EFFECT_TYPE;
	}
	
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(EFFECT_TYPE)) {
			PoisonEffect exist = (PoisonEffect)effect;			
			if (this.getPoisonDamage() > exist.getPoisonDamage()) {
				return true;
			}
		}
		return false;
		
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
