package com.soen6441.core.map;

/**
 * This class is a base class for all validators to test items on the map
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public abstract class PathValidator {

	/*
	 * Mark - Context - Properties
	 */
	
	private GridMap map;
	
	/*
	 * Mark - Context - Getters & Setters
	 */
	
	/**
	 * Method getMap.
	 * @return GridMap
     */
	public GridMap getMap() {
		return map;
	}

	/**
	 * Method setMap.
	 * @param map GridMap
	 */
	public void setMap(GridMap map) {
		this.map = map;
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private String errorMessage; 
	
	/*
	 * Mark - Basic - Methods
	 */
	
	/**
	 * Method validate will check whether a map is valid.
	 * @return boolean 
	 */
	
	abstract public boolean validate();
		
	/*
	 * Mark - Basic - Getters & Setters
	 */

	/**
	 * Method getErrorMessage.
	 * @return String 
     */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/**
	 * Method setErrorMassage.
	 * @param errorMessage String
	 */
	public void setErrorMassage(String errorMessage){
		this.errorMessage = errorMessage;
	}
}
