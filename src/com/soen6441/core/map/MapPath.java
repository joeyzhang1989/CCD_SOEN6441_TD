package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will define the amount of distance the object has to travel along the path in the Map.  
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 */

public class MapPath  {

	private List<MapPoint> locations;
	
	public MapPath() {
		locations = new ArrayList<MapPoint>();
	}
	
	
	/**
	 *The method goAlong will calculate the distance to travel on the path on Map.
	 * 
	 * @param point		A MapPoint object
	 * @param amount	The distance from the MapPoint object represented by a double
	 * @return 			The distance to travel on the Map
	 */
	
	 public double goAlong(MapPoint point , double amount){
		
		double valueToReturn=0.1;//dummy value to return before the full implementation of the method. 
		
		return valueToReturn;
	}


	public List<MapPoint> getLocations() {
		return locations;
	}


	public void setLocations(List<MapPoint> locations) {
		this.locations = locations;
	}
	
	public void addLocation(MapPoint location){
		this.locations.add(location);
	}
}
