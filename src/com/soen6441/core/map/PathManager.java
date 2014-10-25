package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.core.map.Road.Type;
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
	 * Method getMap.
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
	 * Mark - Path to Items Convertion 
	 */
	
	public void generatePathsFromRoadItems (){
		
	}

	/**
	 * This method will geneate {@link Road} object and set them on to the {@link GridMap}
	 * based on the {@link GridMap#getPaths()}
	 */
	public void generateRoadItemsFromPaths (){
		MapPath path = map.getPaths().get(0);
		MapPoint startPoint = map.getStartPoints().get(0);
		MapPoint endPoint = map.getEndPoints().get(0);
		
		Road start = new Road(Type.START);
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
	
		Road end = new Road(Type.END);
		map.setItem(end, endPoint);
	}
	
	private List<MapPoint> pointsInSegment(MapPoint startPoint, MapPoint endPoint) {
		List<MapPoint> points = new ArrayList<MapPoint>();
		
		int dx = endPoint.getGridedX() - startPoint.getGridedX();
		int dy = endPoint.getGridedY() - startPoint.getGridedY();
		
		// make positive to 1, make negative to -1, keep 0 to 0
		if (dx != 0) dx = dx / Math.abs(dx);
		if (dy != 0) dy = dy / Math.abs(dy);
		
		MapPoint delta = new MapPoint(dx, dy);
		
		MapPoint point = startPoint;
		point = point.translatedBy(delta);
		
		while (!point.equals(endPoint)){
			System.out.println(point);
			points.add(point);
			point = point.translatedBy(delta);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		return points;
	}
}
























