package com.soen6441.core.map.validator;

import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.RoadType;

/**
 * The class PathLengthValidator is a subclass of PathValidator. it's repsponsibility is
 * to validate the length of the path.
 * 
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class RoadQuantityValidator extends PathValidator {

	public static final String MISSING_ROAD_ERROR = "There must be at least one road Item connecting startPoint and endPoint";
	
	/**
	 * This method Validate() overrides the method Validate() of it's parent class PathValidator. 
	 * It checks the length of the path and will make sure that the length of path meets the minimum length threshold.
	 * 
     * @return boolean A boolean values that represent whether the length of the path is valid.
     */
	@Override
	public boolean validate() {
		boolean result = true;

		int quantity = 0;
		List<MapItem> mapItems = this.getMap().getAllItems();

		for (int i = 0; i < mapItems.size(); i++) {
			MapItem mapItem = mapItems.get(i);
			if (mapItem instanceof Road) {
				Road road = (Road) mapItem;
				if (road.getRoadType() == RoadType.NORMAL) {
					quantity += 1;
				}
			}
		}

		if (quantity < 1) {
			result = false;
			this.setErrorMassage(MISSING_ROAD_ERROR);
		}

		return result;
	}

}
