package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.tower.Tower;

/**
 * This is a kind of special effect on Tower.
 * By adding this effect to a Tower object, to enhance the properties of the tower object.
 * @author Haiyang Sun
 * @see Effect
 * @see MapItem
 * @see Tower
 * @version $Revision: 1.0 $
 */
public class MotivateEffect extends Effect {
	
	/*
	 * Properties
	 */
	double enhanceRate;
	double enhanceDuration;
	
	/*
	 * Constructor
	 */
	/**
	 * Constructor for MotivateEffect.
	 * @param type String
	 */
	public MotivateEffect(String type) {
		
		this.setType(type);
		
	}
	
	public MotivateEffect() {
			
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
			MotivateEffect exist = (MotivateEffect)effect;			
			if (this.getEnhanceRate() > exist.getEnhanceRate()) {
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
		this.getTimer().setTimeIntervalSecond(enhanceDuration);
		this.getTimer().start();
	}
	
	/**
	 * Method to add a sustaining effect on a critter object.
	 * @see Effect#affect()
	 */
	@Override
	public void affect() {
		
		Tower tower = (Tower)this.getOn();
		tower.reinforce(enhanceRate);
	
	}
	
	/**
	 * Timer tick method: At the ending time of this effect, remove itself.
	 * @see Effect#timerTick(Timer)
	 */
	public void timerTick(Timer timer) {
		
		this.getOn().removeEffect(this);
		this.stop();
		
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
	 * Getters and Setters.
	 */
	/**
	 * Method getEnhanceRate.
	 * @return double
	 */
	public double getEnhanceRate() {
		return enhanceRate;
	}

	/**
	 * Method setEnhanceRate.
	 * @param enhanceRate double
	 */
	public void setEnhanceRate(double enhanceRate) {
		this.enhanceRate = enhanceRate;
	}

	/**
	 * Method getEnhanceDuration.
	 * @return double
	 */
	public double getEnhanceDuration() {
		return enhanceDuration;
	}

	/**
	 * Method setEnhanceDuration.
	 * @param enhanceDuration double
	 */
	public void setEnhanceDuration(double enhanceDuration) {
		this.enhanceDuration = enhanceDuration;
	}

	
	
}
