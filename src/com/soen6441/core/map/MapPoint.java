package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

/**
 * The class MapPoint will define a point on the map by its x and y coordinates.
 * It will also provide  a getter and setter for the MapPoint Object.
 * Moreover this class will provide a method "distanceTo" to find the distance from the MapPoint.
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class MapPoint {
	
	/*
	 * Mark - Constructors
	 */
	
	public MapPoint() {
		super();
	}
	
	/**
	 * Constructor for MapPoint.
	 * @param x double
	 * @param y double
	 */
	public MapPoint(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}


	/*
	 * Mark - Basic - Properties
	 */

	private double x;
	private double y;
	
	/*
	 * Mark - Basic - Methods
	 */
	
	/**
	 * Method getGridedX.
	 * @return int
     */
	public int getGridedX(){
		
	  return (int)this.x;
	}
	
	
	/**
	 * Method setGridedX.
	 * @param x int
	 */
	public void setGridedX(int x){
		
      this.x = x;
	}
	
	
	/**
	 * Method getGridedY.
	 * @return int 
     */
	public int getGridedY(){
			
	   return (int)this.y;
	}
		
	
	/**
	 * Method setGridedY.
	 * @param y int
	 */
	public void setGridedY(int y){
		
	   this.y = y;
	}
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	
	/**
	 * Method getX.
	 * @return double 
     */
	public double getX() {
		return x;
	}


	/**
	 * Method setX.
	 * @param x double
	 */
	public void setX(double x) {
		this.x = x;
	}


	/**
	 * Method getY.
	 * @return double
     */
	public double getY() {
		return y;
	}


	/**
	 * Method setY.
	 * @param y double
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/*
	 * Mark - Functional - Methods
	 */
	
	public double distanceTo(MapPoint point){
		double dx = point.x - this.x;
		double dy = point.y - this.y;
		return Math.pow(dx * dx + dy * dy, 0.5);
	}
	
	public MapPoint add(MapPoint point){
		MapPoint result = new MapPoint();
		result.x = this.x + point.x;
		result.y = this.y + point.y;
		return result;
	}
	
	public MapPoint substract(MapPoint point){
		MapPoint result = new MapPoint();
		result.x = this.x - point.x;
		result.y = this.y - point.y;
		return result;
	}
	
	public MapPoint normalize(){
		
		// make positive to 1, make negative to -1, keep 0 to 0
		double newX = 0;
		double newY = 0;
		
		if (x > 0) newX = 1;
		if (x < 0) newX = -1;
		if (y > 0) newY = 1;
		if (y < 0) newY = -1;

		MapPoint result = new MapPoint();
		result.setX(newX);
		result.setY(newY);
		
		return result;
	}
	
	/*
	 * Mark - Direction Templates - Methods
	 */
	
	public static List<MapPoint> crossDirections(){
		List<MapPoint> directions = new ArrayList<MapPoint>();
		directions.add(new MapPoint(1, 0));
		directions.add(new MapPoint(0, 1));
		directions.add(new MapPoint(-1, 0));
		directions.add(new MapPoint(0, -1));
		return directions;
	}
	
	/*
	 * Mark - Object
	 */

	/**
	 * Method equals.
	 * @param obj Object
	 * @return boolean
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapPoint other = (MapPoint) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MapPoint. x:" + x + ", y:" + y;
	}
}
