package com.soen6441.core.map.validator;

import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.RoadType;

/**
 * The class EndPointValidator is a subclass of PathValidator. It overrides the
 * method Validate() of its parent class and check the number of EndPoints in
 * the Map.
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */

public class EndPointQuantityValidator extends PathValidator {

	public static final String MISSING_ENDPOINT_ERROR = "There is no end point in the map";
	public static final String MULTIPLE_ENDPOINT_ERROR = "There can't be more than one end point in the map";
	
	/**
	 * This method Validate() overrides the method Validate() of parent class PathValidator. 
	 * It checks the Map for end points and validates the Map.
	 * 
	 * @return boolean A boolean values that represent whether # of EndPoints
	 * are valid. 
     */

	@Override
	public boolean validate() {
		boolean result = true;

		int numberOfEndPoints = 0;
		List<MapItem> mapItems = this.getMap().getAllItems();

		for (int i = 0; i < mapItems.size(); i++) {
			MapItem mapItem = mapItems.get(i);
			if (mapItem instanceof Road) {
				Road road = (Road) mapItem;
				if (road.getRoadType() == RoadType.END) {
					numberOfEndPoints += 1;
				}
			}
		}

		if (numberOfEndPoints == 0) {
			result = false;
			this.setErrorMassage(MISSING_ENDPOINT_ERROR);

		} else if (numberOfEndPoints > 1) {
			result = false;
			this.setErrorMassage(MULTIPLE_ENDPOINT_ERROR);
		}

		return result;
	}

}
