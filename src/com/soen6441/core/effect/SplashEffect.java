package com.soen6441.core.effect;

import java.util.List;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapItemSelector;

/**
 * This is a kind of special effect on critter.
 * By adding this effect to a critter instance, 
 * the critter will splash damage to nearby critters within its splash range .
 * @author Haiyang Sun
 * @see Effect
 * @see MapItem
 * @see Critter
 * @version $Revision: 1.0 $
 */
public class SplashEffect extends Effect {
	
	/*
	 * Properties
	 */
	
	int splashDamage;
	double splashRange;
	
	/*
	 * Constructors
	 */
	
	/**
	 * Constructor
	 * @param type
	 */
	public SplashEffect(String type) {
		this.setType(type);
	}
	/**
	 * Constructor
	 */
	public SplashEffect() {

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
			SplashEffect exist = (SplashEffect)effect;			
			if (this.getSplashDamage() > exist.getSplashDamage()) {
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
		
		MapItemSelector selector = this.getOn().getMap().getItemSelector();
		List<MapItem> targets = selector
				.filterByType(Critter.class)
				.filterByCircularArea(this.getOn().getLocation(), this.getSplashRange())
				.filterByExcluding(this.getOn())
				.sortByDirectlyClosestToPoint(this.getOn().getLocation())
				.getItems();
		
		for (MapItem item:targets) {
			Critter critter = (Critter)item;
			critter.damaged((int) this.getSplashDamage());
		}
		this.getOn().removeEffect(this);
		
	}
	

	public int getSplashDamage() {
		return splashDamage;
	}

	public void setSplashDamage(int splashDamage) {
		this.splashDamage = splashDamage;
	}

	public double getSplashRange() {
		return splashRange;
	}

	public void setSplashRange(double splashRange) {
		this.splashRange = splashRange;
	}
	
}
