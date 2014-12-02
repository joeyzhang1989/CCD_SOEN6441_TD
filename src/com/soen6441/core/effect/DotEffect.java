package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.critter.Critter;

/**
 * This is a kind of special effect on critter.
 * By adding this effect to a critter object, to add a DOT on the critter object.
 * @author Haiyang Sun
 * @see Effect
 * @see MapItem
 * @see Critter
 * @version $Revision: 1.0 $
 */
public class DotEffect extends Effect implements TimerListener{
	
	/*
	 * Properties
	 */
	int dotDamage;
	double dotCdTime;
	int dotTimes;

	/*
	 * Constructor
	 */
	/**
	 * Constructor for DotEffect.
	 * @param type String
	 */
	public DotEffect(String type) {
		
		this.setType(type);
		
	}
	public DotEffect() {
		
	}
	
	/*
	 * Methods
	 */
	
	/**
	 * Check if the existing same kind of Effect is stronger than this one.
	 * @see Effect#strongerThan(Effect)
	 */
	@Override
	public boolean strongerThan (Effect effect) {
		
		if (effect.type.equalsIgnoreCase(type)) {
			DotEffect exist = (DotEffect)effect;			
			if (this.getDotDamage() > exist.getDotDamage()) {
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
		
		this.getTimer().setTimeIntervalSecond(this.dotCdTime);
		this.getTimer().setRepeats(true);
		this.getTimer().setRepeatTimes(this.dotTimes);
		this.getTimer().start();		
	}
	
	/**
	 * Method to stop effecting.
	 * @see Effect#stop()
	 */
	@Override
	public void stop() {
		
		this.getTimer().stop();
	}
	

	/**
	 * Timer tick method: Reduce HP of critter.
	 * @see Effect#timerTick(Timer)
	 */
	@Override
	public void timerTick(Timer timer) {

		Critter critter = (Critter)this.getOn();
		
		if (this.getTimer().getCurrentRepeatTimes() == this.dotTimes) {
			
			this.getOn().removeEffect(this);
			critter.damaged(dotDamage);
			
		} else {
			
			critter.damaged(dotDamage);
		}				
	}
	
	/*
	 * Getters and Setters
	 */
	/**
	 * Method getDotDamage.
	 * @return int
	 */
	public int getDotDamage() {
		return dotDamage;
	}
	public void setDotDamage(int dotDamage) {
		this.dotDamage = dotDamage;
	}
	/**
	 * Method getDotCdTime.
	 * @return double
	 */
	public double getDotCdTime() {
		return dotCdTime;
	}
	/**
	 * Method setDotCdTime.
	 * @param dotCdTime double
	 */
	public void setDotCdTime(double dotCdTime) {
		this.dotCdTime = dotCdTime;
	}
	/**
	 * Method getDotTimes.
	 * @return int
	 */
	public int getDotTimes() {
		return dotTimes;
	}
	/**
	 * Method setDotTimes.
	 * @param dotTimes int
	 */
	public void setDotTimes(int dotTimes) {
		this.dotTimes = dotTimes;
	}	
	
}
