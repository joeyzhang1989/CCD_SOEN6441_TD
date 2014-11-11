package com.soen6441.core.effect;

public class SlowEffect extends Effect {
	
	double slowRate;
	double slowDuration;
	public static final String EFFECT_TYPE = "slowEffect";
	
	public SlowEffect () {
		this.type = EFFECT_TYPE;
	}
	
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(EFFECT_TYPE)) {
			SlowEffect exist = (SlowEffect)effect;			
			if (this.getSlowRate() > exist.getSlowRate()) {
				return true;
			}
		}
		return false;
		
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void affect() {
		
	}
	
	@Override
	public void stop() {
		
	}

	public double getSlowRate() {
		return slowRate;
	}

	public void setSlowRate(double slowRate) {
		this.slowRate = slowRate;
	}

	public double getSlowDuration() {
		return slowDuration;
	}

	public void setSlowDuration(double slowDuration) {
		this.slowDuration = slowDuration;
	}
	
}
