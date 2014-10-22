package com.soen6441.core.effect;

/**
 * Any Class wants to have <code>AffectableValue</code> as a property should implement this interface.
 * 
 * @see AffectableValue
 *
 * @author 	Chenglong Zhang
 *
 */
public interface IAffectable {

	/*
	 * Field - Affectable value names
	 */
	
	public static final String AFFECTABLE_VALUE_NAME_RANGE = "RANGE";
	

	/*
	 * Methods
	 */
	
	/**
	 * Add an effect.
	 * You should check whether there is an stronger effect on the object before you call this method.
	 * 
	 * @param effect The effect
	 * 
	
	
	 * 
	 * @see #getEffect(String) * @see Effect#strongerThan(Effect) */
	public void addEffect(Effect effect);
	
	/**
	 * Get an effect by a type name
	 * 
	 * @param type Type name
	
	 * @return Return an <code>Effect</code> object, if there is an effect with the type. Return <code>null</code>, if there is not an effect on the object with the same type. */
	public Effect getEffect(String type);
	
	
	/**
	 * Remove an effect
	 * Any Effect should call this method to remove it self from the affected object
	 * 
	 * @param effect The effect
	 */
	public void removeEffect(Effect effect);

	/**
	 * Get an affectable value by its name.
	 * @param name The name. 
	 * @return Return an <code>AffectableValue</code> object if there is an affectable value with the name.
	 *  Return <code>null</code> if there is not. * @see #AFFECTABLE_VALUE_NAME_RANGE */
	public AffectableValue getAffectableValue (String name);
	
}
