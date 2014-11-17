package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.core.map.Road.RoadType;
import com.soen6441.core.map.validator.ConnectionValidator;
import com.soen6441.core.map.validator.EndPointQuantityValidator;
import com.soen6441.core.map.validator.RoadQuantityValidator;
import com.soen6441.core.map.validator.StartPointQuantityValidator;

/**
 * 
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class PathManager {
	
	/*
	 * Mark - Context
	 */
	
	private GridMap map;
	
	/**
	 * Method getMap
	 * @return GridMap
     */
	public GridMap getMap() {
		return map;
	}

	/**
	 * Method setMap.
	 * @param map GridMap
	 */
	public void setMap(GridMap map) {
		this.map = map;
	}

	/*
	 * Mark - Constuctors 
	 */
	
	public PathManager() {
		validators = new ArrayList<PathValidator>();
		validators.add(new StartPointQuantityValidator());
		validators.add(new EndPointQuantityValidator());
		validators.add(new RoadQuantityValidator());
		validators.add(new ConnectionValidator());
			
	}

	/*
	 * Mark - Validation - Properties
	 */
	
	private List<PathValidator> validators;
	private PathValidator erroredValidator;

	/*
	 * Mark - Validation - Methods
	 */
	
	/**
	 * Calling this method to validate if the items on the map could form a valid path(s)
	 * When the validation falled, call {@link #getErrorMessage()} to get the reason of the failure.
	 * 
     * @return true, if the map is valid to save. false, if the map is not valid.  
     */
	public boolean validate() {
		for (PathValidator validator:validators) {
			validator.setMap(map);
			boolean result = validator.validate();
			if (!result) {
				erroredValidator = validator;
				return false;
			}
		}
		return true;
	}

	/**
	 * Method getErrorMessage.
     * @return String
     */
	public String getErrorMessage() {
		return erroredValidator.getErrorMessage();
	}
	
	/* 
	 * Mark - Path / Items Convertion - Item to Path
	 */
	
	public void generatePathsFromRoadItems(){
		generateStartPointsAndEndPointsFromRoadItems();

		MapPoint startPoint = map.getStartPoints().get(0);
		
		MapPath path = new MapPath();
		MapPoint point = startPoint;
		
		path.addLocation(point);
		
		List<MapPoint> directions = findDirections(point);
		MapPoint direction = null;
		MapPoint backDirection = null;
		
		do {
			if (backDirection != null){
				directions.remove(backDirection);
			}
			direction = directions.get(0);
			
			point = findEndPoint(direction, point);
			path.addLocation(point);
			
			directions = findDirections(point);
			backDirection = new MapPoint().substract(direction);
			
		} while (directions.size() == 2);
		
		List<MapPath> paths = new ArrayList<MapPath>();
		paths.add(path);
		map.setPaths(paths);
	}
	
	private void generateStartPointsAndEndPointsFromRoadItems(){
		List<MapPoint> startPoints = new ArrayList<MapPoint>();
		List<MapPoint> endPoints = new ArrayList<MapPoint>();
		
		List<MapItem> mapItems = map.getAllItems();
		for (MapItem mapItem : mapItems) {
			if (mapItem instanceof Road) {
				Road road = (Road)mapItem;
				if (road.getRoadType() == RoadType.START) {
					startPoints.add(road.getLocation());
				} else if (road.getRoadType() == RoadType.END) {
					endPoints.add(road.getLocation());
				}
			}
		}
		map.setStartPoints(startPoints);
		map.setEndPoints(endPoints);
	}
	
	/**
	 * Method findDirections.
	 * @param point MapPoint
     * @return List<MapPoint>
     */
	private List<MapPoint> findDirections(MapPoint point){
		List<MapPoint> results = new ArrayList<MapPoint>();
		List<MapPoint> directions = MapPoint.crossDirections();
		
		for (MapPoint direction : directions) {
			MapPoint checkPoint = point.add(direction);
			MapItem item = map.getItem(checkPoint);
			if (item != null && item instanceof Road) {
				results.add(direction);
			}
		}
		
		return results;
	}
	
	/**
	 * Method findEndPoint.
	 * @param direction MapPoint
	 * @param fromPoint MapPoint
     * @return MapPoint
     */
	private MapPoint findEndPoint(MapPoint direction, MapPoint fromPoint){
		MapPoint endPoint = fromPoint;
		
		MapPoint nextPoint = endPoint.add(direction);
		MapItem item = map.getItem(nextPoint);
		while (item != null && item instanceof Road) {
			endPoint = nextPoint;
			
			nextPoint = endPoint.add(direction);
			item = map.getItem(nextPoint);
		}
		
		return endPoint;
	}


	/* 
	 * Mark - Path / Items Convertion - Path to Item
	 */
	
	/**
	 * This method will geneate {@link Road} object and set them on to the {@link GridMap}
	 * based on the {@link GridMap#getPaths()}
	 */
	public void generateRoadItemsFromPaths(){
		MapPath path = map.getPaths().get(0);
		MapPoint startPoint = map.getStartPoints().get(0);
		MapPoint endPoint = map.getEndPoints().get(0);
		
		Road start = new Road(RoadType.START);
		map.setItem(start, startPoint);
		
		MapPoint p1 = null;
		MapPoint p2 = null;
		List<MapPoint> locations = path.getLocations();
		for (int i = 1 ; i < locations.size(); i ++){
			p1 = locations.get(i - 1);
			p2 = locations.get(i);
			
			List<MapPoint> points = pointsInSegment(p1, p2);
			for (MapPoint point : points) {
				Road road = new Road();
				map.setItem(road, point);
			}
			if (!p2.equals(endPoint)) {
				Road road = new Road();
				map.setItem(road, p2);
			}
		}
	
		Road end = new Road(RoadType.END);
		map.setItem(end, endPoint);
	}
	
	/**
	 * Method pointsInSegment.
	 * @param startPoint MapPoint
	 * @param endPoint MapPoint
	 * @return List<MapPoint>
     */
	private List<MapPoint> pointsInSegment(MapPoint startPoint, MapPoint endPoint) {
		List<MapPoint> points = new ArrayList<MapPoint>();
		
		MapPoint vector = endPoint.substract(startPoint);
		
		MapPoint delta = vector.normalize();
		
		MapPoint point = startPoint;
		point = point.add(delta);
		
		while (!point.equals(endPoint)){
			points.add(point);
			point = point.add(delta);
		}
		
		return points;
	}
}
























