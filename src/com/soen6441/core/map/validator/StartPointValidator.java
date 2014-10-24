package com.soen6441.core.map.validator;


import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;

/**
 * The class StartPointValidator is a subclass of PathValidator.
 * It overrides the method Validate() of its parent class and check the number of StartPoints in the Map.
 */

public class StartPointValidator extends PathValidator {
	
	
	/**
	 * This method Validate() overrides the method Validate() of parent class PathValidator.
	 * It checks the Map for start points and validates the Map.
	 * 
	 * @return boolean A boolean values that represent whether # of startPoints are valid.
	 */
	
	@Override
	public boolean validate() {
	
	    boolean result=true;
		
		int numberOfStartPoint = 0;
		List<MapItem> mapItems=this.getMap().getAllItems();
		
		for (int i=0;i< mapItems.size();i++){
			   MapItem  mapItem = mapItems.get(i);
			if ( mapItem instanceof Road){
				Road road =(Road)mapItem;
				if(road.getType() == Road.Type.START){
					numberOfStartPoint += 1;
				}
			}
		}
		
		
		if (numberOfStartPoint == 0){
			result=false;
			this.setErrorMassage("There is No start Point In the Map");
		}
		else if(numberOfStartPoint > 1){
			 result=false;
			 this.setErrorMassage("There can't be more than 1 start Point in the Map");
		}
		
		return result;
	
	}
	
	
}
