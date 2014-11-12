package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.critter.Critter;

public class SlowEffect extends Effect implements TimerListener{
	
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
		
		if(this.getOn().getEffect(EFFECT_TYPE) == null ) {
			
			this.getOn().addEffect(this);
			this.affect();
			Timer timer = new Timer();
			timer.setTimerListener(this);
			timer.setDelay((int) this.slowDuration);
			timer.startImmediately();
			
		} else if (this.strongerThan(this.getOn().getEffect(EFFECT_TYPE)) == true) {
			
			this.getOn().getEffect(EFFECT_TYPE).stop();
			this.getOn().addEffect(this);
			this.affect();
			Timer timer = new Timer();
			timer.setTimerListener(this);
			timer.setDelay((int) this.slowDuration);
			timer.startImmediately();
		}
		
	}
	
	@Override
	public void affect() {
		
		Critter critter = (Critter)this.getOn();
		double effectedSpeed = critter.getSpeed().getOriginalValue () * slowRate;
		critter.getSpeed().setEffectedValue(effectedSpeed);
		
	}
	@Override
	public void timerTick(Timer timer) {
		
		this.stop();
		timer.stop();
		
	}
	
	@Override
	public void stop() {
		
		Critter critter = (Critter)this.getOn();
		critter.getSpeed().setEffectedValue(critter.getSpeed().getOriginalValue ());
		this.getOn().removeEffect(this);
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
