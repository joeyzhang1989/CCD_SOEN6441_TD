package com.soen6441.core.map.validator;

import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.RoadType;

/**
 * The class StartPointValidator is a subclass of PathValidator. It overrides the
 * method Validate() of its parent class and check the number of StartPoints in the Map.
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */

public class StartPointQuantityValidator extends PathValidator {

	public static final String MISSING_STARTPOINT_ERROR = "There is no start point in the map";
	public static final String MULTIPLE_STARTPOINT_ERROR = "There can't be more than one start point in the map";
	
	
	/**
	 * This method Validate() overrides the method Validate() of parent class PathValidator. It checks the Map 
	 * for start points and validates the Map.For a map to be valid, it must have exactly one start Point.If 
	 * the # of startPoints is less than or greater than 1, the method sets the appropriate error message 
	 * indicating the error.
	 * 
     * @return boolean A boolean values that represent whether # of startPoints are valid.
     */

	@Override
	public boolean validate() {

		boolean result = true;

		int numberOfStartPoint = 0;
		List<MapItem> mapItems = this.getMap().getAllItems();

		for (int i = 0; i < mapItems.size(); i++) {
			MapItem mapItem = mapItems.get(i);
			if (mapItem instanceof Road) {
				Road road = (Road) mapItem;
				if (road.getRoadType() == RoadType.START) {
					numberOfStartPoint += 1;
				}
			}
		}

		if (numberOfStartPoint == 0) {
			result = false;
			this.setErrorMassage(MISSING_STARTPOINT_ERROR);

		} else if (numberOfStartPoint > 1) {
			result = false;
			this.setErrorMassage(MULTIPLE_STARTPOINT_ERROR);
		}

		return result;

	}

}
