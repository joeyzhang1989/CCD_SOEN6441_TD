package com.soen6441.core.map;





/**
 * This class will check whether the map is valid or not.In addition, this class
 * will provide methods to generate paths on the map  
 * 
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public abstract class PathValidator {

	private GridMap map;
	private String errorMessage; 
	
	/**
	 * Method validate will check whether a map is valid.
	 * @return boolean */
	
	abstract public boolean validate();
		
		
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
