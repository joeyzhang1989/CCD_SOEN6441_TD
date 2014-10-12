package com.soen6441.core.effect;

/**
 * Any value will be effected should use this class as a type.
 * The originalValue is the original value.
 * The effectedValue will be calculated by the class who has a property of this type
 * 
 * @author Zhe Zhao
 *
 */
public class AffectableValue implements Cloneable {
	
	/*
	 * Properties
	 */
	
	/**
	 * The originalValue is the original value.
	 * @author Zhe Zhao
	 */
	private double originalValue ;
	
	/**
	 * The effectedValue will be calculated by the class who has a property of this type
	 * @author Zhe Zhao
	 */
	private double effectedValue;

	/* 	
	 * Constructors 
	 */
	
	/**
	 * An general constructor
	 */
	public AffectableValue() {
		super();
	}

	/**
	 * An construcutor 
	 * 
	 */
	public AffectableValue(double originalValue ) {
		super();
		this.originalValue  = originalValue ;
	}
	
	/*
	 * Getters and Setters
	 */
	
	public double getOriginalValue () {
		return originalValue ;
	}
	public void setOriginalValue (double originalValue ) {
		this.originalValue  = originalValue ;
	}
	public double getEffectedValue() {
		return effectedValue;
	}
	public void setEffectedValue(double effectedValue) {
		this.effectedValue = effectedValue;
	}
	
	/*
	 * Object
	 */
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
