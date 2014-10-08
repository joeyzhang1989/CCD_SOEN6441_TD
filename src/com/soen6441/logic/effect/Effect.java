package com.soen6441.logic.effect;

/**
 * 
 * Any spicial thing which will temperarly change the <code>AffectableValue</code> should use this class to make it happen.
 * 
 * @see AffectableValue
 *
 */
public class Effect {
	
	/*
	 * Properties
	 */
	
	protected String type;
	private IAffectable on;
	
	/*
	 * Constructors
	 */
	
	public Effect() {
		super();
	}

	public Effect(IAffectable on) {
		super();
		this.on = on;
	}
	

	/*
	 * Methods
	 */
	
	/**
	 * Test if this effect is stronger to another effect.
	 * The another effect should be same type as this effect in order to compare.
	 * Use <code>getType</code> before calling this method.
	 * 
	 * @param effect Another effect, which should be same type as this one.
	 * @return <code>true</code> if this effect is stronger, <code>false</code> if the effect in the para is stronger or they are imcomparable.
	 * 
	 * @see #getType()
	 * 
	 */
	public boolean strongerThan(Effect effect){
		return false;
	}
	
	/**
	 * To start the effect
	 * This method should be called after putting this object to the affectable object.
	 */
	public void affect(){
		
	}

	/*
	 * Getters and Setters
	 */
	
	public String getType() {
		return type;
	}
	
	public IAffectable getOn() {
		return on;
	}
	
	public void setOn(IAffectable on) {
		this.on = on;
	}
}
