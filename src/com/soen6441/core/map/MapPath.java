package com.soen6441.core.map;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;

/**
 * This class will define the distance the object has to travel along the path in the Map.  
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class MapPath implements IArchive{
	
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
		int index = 0;
		MapPoint startPoint = null;
		MapPoint endPoint = null;
		for (index = 0 ; index < locations.size() - 1; index++){
			startPoint = locations.get(index);
			endPoint = locations.get(index + 1);
			if (isInSegment(point, startPoint, endPoint)) {
				break;
			}
		}
		
		double rest;
		MapPoint checkPoint = point;
		while (index < locations.size() - 1) {
			startPoint = locations.get(index);
			endPoint = locations.get(index + 1);
			
			rest = goAlongInSegment(checkPoint, amount, startPoint, endPoint);
			if (rest == 0) {
				endPoint = locations.get(index + 1);
				MapPoint vector = endPoint.substract(checkPoint).normalize().scale(amount);
				MapPoint newPoint = checkPoint.add(vector);
				point.setX(newPoint.getX());
				point.setY(newPoint.getY());
				return 0;
			}
			
			amount = rest;
			checkPoint = endPoint;
			index ++;
		}
				
		return amount;
	}
	
	private double goAlongInSegment(MapPoint point , double amount, MapPoint startPoint, MapPoint endPoint){
		double distanceToEnd = point.distanceTo(endPoint);
		if (amount > distanceToEnd) {
			return amount - distanceToEnd;
		} else {
			return 0;
		}
	}

	private boolean isInSegment(MapPoint point , MapPoint startPoint, MapPoint endPoint) {
		double x = point.getX();
		double y = point.getY();
		double x1 = startPoint.getX();
		double y1 = startPoint.getY();
		double x2 = endPoint.getX();
		double y2 = endPoint.getY();
		
		if ((x == x1 && x == x2) && (y >= y1 && y < y2)) return true;
		if ((y == y1 && y == y2) && (x >= x1 && x < x2)) return true;
		return false;
	}
	/*
	 * Mark - Archive - Methods
	 */

	public class NameForArchiving {
		public static final String Class = "MapPath";
		public static final String LOCATIONS = "locations";

	}

	@Override
	public void decode(Element element) {
		Node locations = element.selectSingleNode("locations");
		@SuppressWarnings("unchecked")
		List<Node> pointNodes = locations.selectNodes("MapPoint");

		for (Node pointNode : pointNodes) {
			Element pointElement = (Element) pointNode;
			MapPoint mapPoint = new MapPoint();
			mapPoint.decode(pointElement);
			
			this.addLocation(mapPoint);
		}
	}

	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class);
		Element locations = element.addElement(NameForArchiving.LOCATIONS);

		for (MapPoint mapPoint : this.locations) {
			locations.add(mapPoint.encode());
		}

		return element;
	}

}
