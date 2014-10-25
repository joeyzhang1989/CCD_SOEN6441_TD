package com.soen6441.core.map;

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
	
	/**
	 * The method distanceTo will calculate the distance of the calling Object from a certain MapPoint
	 * 
	 * @param point  A MapPoint Object
	
	 * * @return  distance to the MapPoinit */
	
	public double distanceTo(MapPoint point){
		
		double distance=1.0;//dummy value. must be removed after actual implementation
		return distance;
	}
	
	public MapPoint translatedBy(MapPoint point){
		MapPoint result = new MapPoint();
		result.x = this.x + point.x;
		result.y = this.y + point.y;
		return result;
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
}
