package com.soen6441.core.map;




/**
 * This class will check whether the map is valid or not.In addition, this class
 * will provide methods to generate paths on the map  
 * 
 * @author Mohammad Ali
 */

public abstract class PathValidator {

	private GridMap map;
	
	protected String errorMessage; 

	/**
	 * Method validate.
	 * @return boolean
	 */
	public abstract boolean validate();
	
	public GridMap getMap() {
		return map;
	}

	public void setMap(GridMap map) {
		this.map = map;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
	
	protected void setErrorMassage(String errorMessage){
		this.errorMessage = errorMessage;
	}
}
