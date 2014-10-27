package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will define the distance the object has to travel along the path in the Map.  
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class MapPath  {
	
	/*
	 * Mark - Constructors
	 */

	public MapPath() {
		locations = new ArrayList<MapPoint>();
	}
	

	/*
	 * Mark - Location - Properties
	 */
	
	private List<MapPoint> locations;
	
	/*
	 * Mark - Location - Methods
	 */
	
	/**
	 * Method getFirstLocation.
	 * @return MapPoint
	 */
	public MapPoint getFirstLocation(){
		return locations.get(0);
	}
	 
	/**
	 * Method getLastLocation.
	 * @return MapPoint
	 */
	public MapPoint getLastLocation(){
		return locations.get(locations.size() - 1);
	}
	
	/**
	 * Method addLocation.
	 * @param location MapPoint
	 */
	public void addLocation(MapPoint location){
		this.locations.add(location);
	}
	
	/*
	 * Mark - Location - Getters & Setters
	 */

	/**
	 * Method getLocations.
     * @return List<MapPoint>  
     */
	public List<MapPoint> getLocations() {
		return locations;
	}

	/**
	 * Method setLocations.
	 * @param locations List<MapPoint>
	 */
	public void setLocations(List<MapPoint> locations) {
		this.locations = locations;
	}

	/*
	 * Mark - Functional - Methods
	 */
	
	/**
	 *The method goAlong will calculate the distance to travel on the path on Map.
	 * 
	 * @param point		A MapPoint object
	 * @param amount	The distance from the MapPoint object represented by a double
	 * @return 			The distance to travel on the Map  
     */
	
	public double goAlong(MapPoint point , double amount){
		return 0;
	}
	
}
