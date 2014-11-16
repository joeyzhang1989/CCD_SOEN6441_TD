package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.critter.Critter;
/**
 * This is a kind of  special effect on critter.
 * By adding this effect to a critter object, to reduce the running speed of the critter.
 * @author Haiyang Sun
 * @see Effect
 * @see MapItem
 * @see Critter
 */

public class SlowEffect extends Effect {
	
	/*
	 * Properties
	 */
	double slowRate;
	double slowDuration;
	
	/*
	 * Constructor
	 */
	public SlowEffect(String type) {
		
		this.setType(type);
		
	}
	
	public SlowEffect() {
			
	}
	
	/*
	 * Methods
	 */
	
	/**
	 * Check if the existing Slow Effect is stronger than this one.
	 * @see Effect#strongerThan(Effect)
	 */
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(type)) {
			SlowEffect exist = (SlowEffect)effect;			
			if (this.getSlowRate() > exist.getSlowRate()) {
				return true;
			}
		}
		return false;
		
	}
	
	
	/**
	 * Method to start the effect.
	 * @see Effect#start()
	 */
	@Override
	public void start() {
		this.getTimer().setTimeIntervalSecond(this.getSlowDuration());
		this.getTimer().start();
	}
	
	/**
	 * Method to add a sustaining effect on a critter object.
	 * @see Effect#affect()
	 */
	@Override
	public void affect() {
		
		Critter critter = (Critter)this.getOn();
		double effectedSpeed = critter.getSpeed().getOriginalValue () * slowRate;
		critter.getSpeed().setEffectedValue(effectedSpeed);
		
	}
	
	/**
	 * Timer tick method: At the ending time of this effect, remove itself.
	 * @see Effect#timerTick(Timer)
	 */
	@Override
	public void timerTick(Timer timer) {
		
		this.getOn().removeEffect(this);
		
	}
	
	/**
	 * Method to stop effecting.
	 * @see Effect#stop()
	 */
	@Override
	public void stop() {
		
		this.getTimer().stop();
		
	}
	
	/*
	 * Getters and Setters
	 */
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
