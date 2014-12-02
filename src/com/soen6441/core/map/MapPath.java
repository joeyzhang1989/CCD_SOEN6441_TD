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
	 * @return MapItemSelector
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
	
	/**
	 * Method goAlongInSegment.
	 * @param point MapPoint
	 * @param amount double
	 * @param startPoint MapPoint
	 * @param endPoint MapPoint
	 * @return double
	 */
	private double goAlongInSegment(MapPoint point , double amount, MapPoint startPoint, MapPoint endPoint){
		double distanceToEnd = point.distanceTo(endPoint);
		if (amount > distanceToEnd) {
			return amount - distanceToEnd;
		} else {
			return 0;
		}
	}

	/**
	 * Method isInSegment.
	 * @param point MapPoint
	 * @param startPoint MapPoint
	 * @param endPoint MapPoint
	 * @return boolean
	 */
	private boolean isInSegment(MapPoint point , MapPoint startPoint, MapPoint endPoint) {
		if (point.equals(startPoint)) return true;
				
		double x = point.getX();
		double y = point.getY();
		double x1 = startPoint.getX();
		double y1 = startPoint.getY();
		double x2 = endPoint.getX();
		double y2 = endPoint.getY();
		
		double minX = Math.min(x1, x2);
		double maxX = Math.max(x1, x2);
		double minY = Math.min(y1, y2);
		double maxY = Math.max(y1, y2);
		
		if ((x == x1 && x == x2) && (y > minY && y < maxY)) return true;
		if ((y == y1 && y == y2) && (x > minX && x < maxX)) return true;
		
		return false;
	}
	
	/**
	 * Method distanceToLastLocation.
	 * @param point MapPoint
	 * @return double
	 */
	public double distanceToLastLocation (MapPoint point) {
		double sum = 0;
		
		int index = 0;
		MapPoint startPoint = null;
		MapPoint endPoint = null;
		for (index = 0 ; index < locations.size() - 1; index++){
			startPoint = locations.get(index);
			endPoint = locations.get(index + 1);
			if (isInSegment(point, startPoint, endPoint)) {
				sum += point.distanceTo(endPoint);
				break;
			}
		}
		

		for (index = index + 1; index < locations.size() - 1; index++){
			startPoint = locations.get(index);
			endPoint = locations.get(index + 1);
			sum += startPoint.distanceTo(endPoint);
		}
		
		return sum;
	}
	
	/*
	 * Mark - Archive - Methods
	 */

	/**
     * @author Zhe Zhao
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving {
		public static final String Class = "MapPath";
		public static final String LOCATIONS = "locations";

	}

	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		Node locations = element.selectSingleNode(NameForArchiving.LOCATIONS);
		@SuppressWarnings("unchecked")
		List<Node> pointNodes = locations.selectNodes(MapPoint.NameForArchiving.Class);

		for (Node pointNode : pointNodes) {
			Element pointElement = (Element) pointNode;
			MapPoint mapPoint = new MapPoint();
			mapPoint.decode(pointElement);
			
			this.addLocation(mapPoint);
		}
	}

	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
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
