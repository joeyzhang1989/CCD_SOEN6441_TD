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
public class PoisonEffect extends Effect implements TimerListener{
	
	/*
	 * Properties
	 */
	int poisonDamage;
	double poisonCdTime;
	int poisonTimes;

	/*
	 * Constructor
	 */
	/**
	 * Constructor for PoisonEffect.
	 * @param type String
	 */
	public PoisonEffect(String type) {
		
		this.setType(type);
		
	}
	public PoisonEffect() {
		
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
			PoisonEffect exist = (PoisonEffect)effect;			
			if (this.getPoisonDamage() > exist.getPoisonDamage()) {
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
		
		this.getTimer().setTimeIntervalSecond(this.poisonCdTime);
		this.getTimer().setRepeats(true);
		this.getTimer().setRepeatTimes(this.poisonTimes);
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
		
		if (this.getTimer().getCurrentRepeatTimes() == this.poisonTimes) {
			
			this.getOn().removeEffect(this);
			critter.damaged(poisonDamage);
			
		} else {
			
			critter.damaged(poisonDamage);
		}				
	}
	
	/*
	 * Getters and Setters
	 */

	/**
	 * Method getPoisonDamage.
	 * @return int
	 */
	public int getPoisonDamage() {
		return poisonDamage;
	}

	/**
	 * Method setPoisonDamage.
	 * @param poisonDamage int
	 */
	public void setPoisonDamage(int poisonDamage) {
		this.poisonDamage = poisonDamage;
	}

	/**
	 * Method getPoisonCdTime.
	 * @return double
	 */
	public double getPoisonCdTime() {
		return poisonCdTime;
	}

	/**
	 * Method setPoisonCdTime.
	 * @param poisonCdTime double
	 */
	public void setPoisonCdTime(double poisonCdTime) {
		this.poisonCdTime = poisonCdTime;
	}

	/**
	 * Method getPoisonTimes.
	 * @return int
	 */
	public int getPoisonTimes() {
		return poisonTimes;
	}

	/**
	 * Method setPoisonTimes.
	 * @param poisonTimes int
	 */
	public void setPoisonTimes(int poisonTimes) {
		this.poisonTimes = poisonTimes;
	}

	
	
}
