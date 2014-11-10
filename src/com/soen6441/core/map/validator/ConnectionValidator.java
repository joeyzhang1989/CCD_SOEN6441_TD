package com.soen6441.core.map.validator;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.PathValidator;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.RoadType;

/**
 * The class ConnectedPathValidator is a subclass of PathValidator.
 * It overrides the method Validate() of its parent class and checks whether there is a connected
 * path between startPoint and EndPoint.
 * 
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class ConnectionValidator extends PathValidator {

	public static final String BROKEN_PATH_ERROR = "The Road is not connected";
	public static final String MULTIPLE_BRANCH_ERROR = "Branches are not allowed";
	public static final String STARTPOINT_BRANCH_ERROR = "Start point should have only one neighbour" ;
	public static final String ENDPOINT_BRANCH_ERROR = "End point should have only one neighbour";
	
	
	
	/**
	 * This method Validate() overrides the method Validate() of parent class PathValidator.
	 * It checks whether a path exist between startPoint and EndPoint.
	 * 
	 * @return boolean A boolean values that represent whether a Path exist between startPoint and EndPoint. 
     */
	
	@Override
	public boolean validate() {
		
		List<MapItem> mapItems = this.getMap().getAllItems();
		List<Road> roadItems = new ArrayList<Road>();
		
		/*
		 * Below we are seperating the road items from the list of all items.
		 * All orad items will be saved int roadItems List for further Validation.
		 */
		
		for (int i=0; i < mapItems.size();i++) {
		    MapItem  aItem = mapItems.get(i);
			if ( aItem instanceof Road) {
				Road road =(Road)aItem;
				roadItems.add(road);
			}
		}
		
		/*
		 * Below we will check all MapItem of Type Road and eill calculate their neighbours by calling a
		 * helper method NumberOfNeighbours().
		 */
		
		for (Road road : roadItems) {
		    int totalNeighbours = numberOfNeighbours(road);
		    RoadType type = road.getRoadType();
		    
		    if(type == RoadType.START) {
		    	if(totalNeighbours > 1) {
		    		this.setErrorMassage(STARTPOINT_BRANCH_ERROR);
		    		return false;
		    	} else if (totalNeighbours < 1) {
		    		this.setErrorMassage(BROKEN_PATH_ERROR);
		    		return false;
		    	}
		    } else if(type == RoadType.END) {
		    	if(totalNeighbours > 1) {
		    		this.setErrorMassage(ENDPOINT_BRANCH_ERROR);
		    		return false;
		    	} else if (totalNeighbours < 1) {
		    		this.setErrorMassage(BROKEN_PATH_ERROR);
		    		return false;
		    	}
		    } else if(type == RoadType.NORMAL) {
		    	if(totalNeighbours > 2) {
		    		this.setErrorMassage(MULTIPLE_BRANCH_ERROR);
		    		return false;
		    	} else if (totalNeighbours < 2) {
		    		this.setErrorMassage(BROKEN_PATH_ERROR);
		    		return false;
		    	}
		    } 
		}
		
		return true;
	}
	
	
	/**
	 * NumberOfNeighbours is a helper method that gets a road object and count its nwighbours.
	 * For a Map with one entry and exit point and only one path bwtween entry and exit. Every road object must has 
	 * exactly two neighbours unless it is Entry or Exit Point in which case It must have only ont connected neighbour,
	 * 
	 * @param road  A Road Object
	 * @return numberOfNeighbours  
     */
	
	private int numberOfNeighbours(Road road) {
		
		List<MapPoint> directions = MapPoint.crossDirections();
		
		/*
		 * Check the neighbours of the given mapPoints and return the one that is part of path.
		 * Limitation: Only work on a single Path.If multiple paths have to be supported we then the method 
		 * must return a list of MapPoints rather than a single MapPoint.
		 */
		int number = 0;
		for (MapPoint direction : directions) {
			MapPoint checkPoint = road.getLocation().add(direction);
			MapItem item = this.getMap().getItem(checkPoint);
			if(item != null && item instanceof Road) {
				number += 1;
			}
		}
		return number;
	}
}
