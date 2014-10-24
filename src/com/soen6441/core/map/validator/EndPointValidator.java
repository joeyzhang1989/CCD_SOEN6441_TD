package com.soen6441.core.map.validator;

import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.Type;

/**
 * The class EndPointValidator is a subclass of PathValidator.
 * It overrides the method Validate() of its parent class and check the number of EndPoints in the Map.
 * 
 */


public class EndPointValidator extends PathValidator{

	/**
	 * This method Validate() overrides the method Validate() of parent class PathValidator.
	 * It checks the Map for end points and validates the Map.
	 * 
	 * @return boolean A boolean values that represent whether # of EndPoints are valid.
	 */
	
	@Override
	public boolean validate() {
		boolean result=true;
		
		int numberOfEndPoints = 0;
		List<MapItem> mapItems=this.getMap().getAllItems();
		
		for (int i=0;i< mapItems.size();i++){
			   MapItem  mapItem = mapItems.get(i);
			if ( mapItem instanceof Road){
				Road road =(Road)mapItem;
				if(road.getType() == Road.Type.END){
					numberOfEndPoints += 1;
				}
			}
		}
		
		if (numberOfEndPoints<1){
			result=false;
			this.setErrorMassage("There is No End Point In the Map");
		}
	
		return result;	
	}

}
