package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

/**
 * This class was designed to be the core logic of the map.
 * 
 * @author Zhe Zhao
 *
 */
public class GridMap {
	
	/*
	 * Mark - Constructors
	 */
	
	public GridMap() {
		this.pathManager = new PathManager();
	}
	
	/*
	 * Mark - Grided Item Management - Properties
	 */
	
	private int width = 2;
	private int height = 2;
	private MapItem[][] items; 
	
	/*
	 * Mark - Grided Item Management - Methods
	 */
	
	/**
	 * Set a grided map item on to the map.
	 * This method should be used only if the item will be place on grid.
	 * The connection between <code>item</code> and <code>location</code> will be setup in this method.
	 * 
	 * @param item		The item
	 * @param location	An grided location where put the item
	 */
	public void setItem(MapItem item, MapPoint location){
		item.setLocation(location);
		items[location.getGridedY()][location.getGridedX()] = item;
	}
	
	/**
	 * Get a grided map item on the map
	 * 
	 * @param location An grided location you want to check
	
	 * @return Return the item if there is, or return null if there isn't, or return null if the location is out of the boundary. 
	 */
	public MapItem getItem(MapPoint location){
		int x = location.getGridedX();
		int y = location.getGridedY();
		
		// boundary check
		if (x < 0 || y < 0 || x >= width || y >= height){
			return null;
		}
		
		// get the one
		return items[y][x];
	}
	
	/**
	 * Remove a grided map item from the map
	 * Before calling this method, you should call <code>getItem</code> first.
	 * 
	 * @param item An grided item which you want to remove.
	 * 
	
	 * 
	 * @see #getItem(MapPoint) 
	 */
	public void removeItem(MapItem item){
		MapPoint location = item.getLocation();
		items[location.getGridedY()][location.getGridedX()] = null;
	}
	
	/*
	 * Mark - Grided Item Management - Getters & Setters
	 */

	/**
	 * Method getWidth.
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Method setWidth.
	 * @param width int
	 */
	public void setWidth(int width) {
		this.width = width;
		regenerateGrid();
	}

	/**
	 * Method getHeight.
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Method setHeight.
	 * @param height int
	 */
	public void setHeight(int height) {
		this.height = height;
		regenerateGrid();
	}
	
	private void regenerateGrid(){
		items = new MapItem[this.height][this.width];
	}
	
	/*
	 * Mark - Selection - Properties
	 */

	
	/*
	 * Mark - Path - Properties
	 */
	
	private List<MapPoint> startPoints;
	private List<MapPoint> endPoints;
	private List<MapPath> paths;
	private PathManager pathManager;

	/*
	 * Mark - Path - Methods
	 */
	

	/**
	 * Get any path which start from a specific point
	 * If there is no branches in the game, it will give you only one path
	 * 
	 * @param point The location
	
	 * @return It will return all the paths which start from the point. If there is no path start from the point, it will return an list object but no object in it. 
	 */
	public List<MapPath> pathFrom(MapPoint point){
		List<MapPath> resultPaths = new ArrayList<MapPath>();
		for(MapPath path : paths){
			if (path.getLocations().get(0).equals(point)){
				resultPaths.add(path);
			}
		}
		return resultPaths;
	}
	

	/*
	 * Mark - Path - Getters & Setters
	 */

	/**
	 * Method getStartPoints.
	 * @return List<MapPoint>
	 */
	public List<MapPoint> getStartPoints() {
		return startPoints;
	}

	/**
	 * Method setStartPoints.
	 * @param startPoints List<MapPoint>
	 */
	public void setStartPoints(List<MapPoint> startPoints) {
		this.startPoints = startPoints;
	}

	/**
	 * Method getEndPoints.
	 * @return List<MapPoint>
	 */
	public List<MapPoint> getEndPoints() {
		return endPoints;
	}

	/**
	 * Method setEndPoints.
	 * @param endPoints List<MapPoint>
	 */
	public void setEndPoints(List<MapPoint> endPoints) {
		this.endPoints = endPoints;
	}

	/**
	 * Method getPaths.
	 * @return List<MapPath>
	 */
	public List<MapPath> getPaths() {
		return paths;
	}

	/**
	 * Method setPaths.
	 * @param paths List<MapPath>
	 */
	public void setPaths(List<MapPath> paths) {
		this.paths = paths;
	}

	public PathManager getPathManager() {
		return pathManager;
	}

	
	
}
