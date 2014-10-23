package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;




/**
 * This class will check whether the map is valid or not.In addition, this class
 * will provide methods to generate paths on the map  
 * 
 * @author Mohammad Ali
 */

public abstract class PathValidator {

	private GridMap map;
	private String errorMessage; 
	
	/**
	 * Method validate will check whether a map is valid.
	 * @param GridMap 
	 * @return boolean
	 */
	
	abstract public boolean validate();
		
		
	public GridMap getMap() {
		return map;
	}

	public void setMap(GridMap map) {
		this.map = map;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
	
	abstract public void setErrorMassage(String errorMessage);
		
	
	
	
}
