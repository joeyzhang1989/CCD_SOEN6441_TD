package com.soen6441.core.map;

/**
 * The class MapPoint will define a point on the map by its x and y coordinates.
 * It will also provide getter and setter for MapPoint Object.
 * Moreover this class will provide a method "distanceTo" to find the distance from the MapPoint.
 * 
 * @author Mohammad Ali
 * @since version 1.0
 */

public class MapPoint {

	private double x;
	private double y;
	
	/*
	 *Public  Getter and Setters for x and y
	 */
	
	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	/*
	 * getGridedX() and getGridedY() getters to get the valuer of x and y as an Int
	 * setGridedX() and setGridedY() setters to set the value of  x and y from an int to a double.
	 */
	
	
	public int getGridedX(){
		
	  return  (int)this.x;
	}
	
	
	public void setGridedX(int newX){
		
      this.x=(double) newX;
	}
	
	
	public int getGridedY(){
			
	   return  (int)this.y;
	}
		
	
	public void setGridedY(int newY){
		
	   this.y=(double) newY;
	}
	
	
	/**
	 * The method distanceTo will calculate the distance of the calling Object from a certain MapPoint
	 * 
	 * @param point  A MapPoint Object
	 * @return  distance to the MapPoinit
	 * */
	
	public double distanceTo(MapPoint point){
		
		double distance=1.0;//dummy value. must be removed after actual implementation
		return distance;
	}
	
	
}
