package com.soen6441.core.effect;

/**
 * 
 * Any spatial thing which will temporarily change the <code>AffectableValue</code> should use this class to make it happen.
 * 
 * @see AffectableValue
 *
 * @author Zhe Zhao
 * @author Chenglong Zhang
 * 
 * @version $Revision: 1.0 $
 */
public class Effect {
	
	/*
	 * Properties
	 */
	
	protected String type;
	
	
	/*
	 * Constructors
	 */
	
	public Effect() {
		super();
	}

	/**
	 * Constructor for Effect.
	 * @param on IAffectable
	 */
	/*
	public Effect(IAffectable on) {
		super();
		this.on = on;
	}
	*/

	/*
	 * Methods
	 */
	
	/**
	 * Test if this effect is stronger to another effect.
	 * The another effect should be same type as this effect in order to compare.
	 * Use <code>getType</code> before calling this method.
	 * 
	 * @param effect Another effect, which should be same type as this one.
	 * @return <code>true</code> if this effect is stronger, 
	 * 			<code>false</code> if the effect in the para is stronger or they are imcomparable. 
	 * 
	 * @see #getType() 
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

	public void start() {
		
	}
	
	public void affectOnAffectableValues() {
		
	}
	
	public void stop() {
		
	}
	/*
	 * Getters and Setters
	 */
	
	/**
	 * Method getType.
     * @return String 
     */
	public String getType() {
		return type;
	}
	
	/**
	 * Method getOn.
 	 * @return IAffectable  
     */
	

	public void setType(String type) {
		this.type = type;
	}
}
