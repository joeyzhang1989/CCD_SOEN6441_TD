package com.soen6441.core.map;

import java.util.List;

public class GridMap {
	
	/*
	 * Properties
	 */
	
	private int width;
	private int height;
	private int[][] items;
	
	private List<MapPoint> startPoints;
	private List<MapPoint> endPoints;
	private List<MapPath> paths;
	
	/*
	 * Methods - Grided Item Management
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
		
	}
	
	/**
	 * Get a grided map item on the map
	 * 
	 * @param location An grided location you want to check
	 * @return Return the item if there is, or return null if there isn't
	 */
	public MapItem getItem(MapPoint location){
		return null;
	}
	
	/**
	 * Remove a grided map item from the map
	 * Before calling this method, you should call <code>getItem</code> first.
	 * 
	 * @param item An grided item which you want to remove.
	 * 
	 * @see #getItem(MapPoint)
	 * 
	 */
	public void removeItem(MapItem item){
		
	}
	

	/*
	 * Methods - Path
	 */
	
	/**
	 * Get any path which start from a specific point
	 * If there is no branches in the game, it will give you only one path
	 * 
	 * @param point The location
	 * @return It will return all the paths which start from the point. If there is no path start from the point, it will return an list object but no object in it.
	 */
	public List<MapPath> pathFrom(MapPoint point){
		return null;
	}
	
}
