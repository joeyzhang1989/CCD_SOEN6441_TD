package com.soen6441.core.map.validator;

import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;

/**
 * The class PathLengthValidator will validate the length of the path.
 * 
 * @author Mohammad Ali
 */

public class RoadQuantityValidator extends PathValidator {


	/**
	 * This method Validate() overrides the method Validate() of parent class PathValidator.
	 * It checks the length of the path and will make sure that the path is valid.
	 * 
	 * @return boolean A boolean values that represent whether the length of the path is valid.
	 */
	@Override
	public boolean validate() {
		boolean result = true;
		
		int quantity = 0;
		List<MapItem> mapItems = this.getMap().getAllItems();
		
		for (int i=0; i < mapItems.size();i++) {
			   MapItem  mapItem = mapItems.get(i);
			if ( mapItem instanceof Road) {
				Road road = (Road) mapItem;
				if(road.getType() == Road.Type.NORMAL) {
					quantity += 1;
				}
			}
		}
		
		if (quantity < 1) {
			result = false;
			this.setErrorMassage("There must be at least one road");
		}
		
		return result;
	}

}
