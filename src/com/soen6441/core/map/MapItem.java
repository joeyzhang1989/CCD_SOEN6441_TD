package com.soen6441.core.map;


import java.util.Map;
import java.util.Observable;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.Effect;
import com.soen6441.core.effect.IAffectable;


/**
 * This class will define the distance the object has to travel along the path in the Map.  
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali

 * @version $Revision: 1.0 $
 */

public class MapItem extends Observable implements IAffectable, IArchive{

	/*
	 * Mark - Basic - Properties
	 */

	private GridMap map;
	private MapPoint location;
	
	/*
	 * Mark - Basic - Getters & Setters
	 */

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

	/**
	 * Method getLocation.
     * @return MapPoint
     */
	public MapPoint getLocation() {
		return location;
	}

	/**
	 * Method setLocation.
	 * @param location MapPoint
	 */
	public void setLocation(MapPoint location) {
		this.location = location;
	}
	
	/*
	 * Mark - Display - Properties
	 */
	
	private String name;
	private String cellImageName;
	
	/*
	 * Mark - Display - Getters & Setters
	 */

	/**
	 * Method getName.
     * @return String  
     */
	public String getName() {
		return name;
	}

	/**
	 * Method setName.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Method getCellImageName.
	 * @return String 
     */
	public String getCellImageName() {
		return cellImageName;
	}

	/**
	 * Method setCellImageName.
	 * @param cellImageName String
	 */
	public void setCellImageName(String cellImageName) {
		this.cellImageName = cellImageName;
	}
	
	
	/*
	 * Mark - Effect - Properties
	 */

	private Map<String , Effect> effects;
	
	/*
	 * Mark - Effect - Methods
	 */
	private void updateAffectableValues(){
		
	}

    /*
     * Mark - Effect - Implement IAffectable
     */
	       
	/**
     * Method addEffect.
     * @param effect Effect
     * @see com.soen6441.core.effect.IAffectable#addEffect(Effect)
     */
     @Override
	public void addEffect(Effect effect) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Method getEffect.
	 * @param type String
     * @return Effect  
     * @see com.soen6441.core.effect.IAffectable#getEffect(String)  
     */
	@Override
	public Effect getEffect(String type) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Method removeEffect.
	 * @param effect Effect
	
     * @see com.soen6441.core.effect.IAffectable#removeEffect(Effect) */
	@Override
	public void removeEffect(Effect effect) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Method getAffectableValue .
	 * @param name String
     * @return AffectableValue  
     * @see com.soen6441.core.effect.IAffectable#getAffectableValue(String)
     */
	@Override
	public AffectableValue getAffectableValue (String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * Mark - Archive - Methods
	 */
	 
	public class NameForArchiving{
		public static final String Class = "MapItem";
		private static final String Location = "location";
		private static final String Name = "name";
		private static final String CellImageName = "cellImageName";
	}

	@Override
	public void decode(Element element) {
		Element locationElement = element.element(NameForArchiving.Location);
		
		if (locationElement != null) {
			MapPoint location = new MapPoint();
			location.decode(locationElement.element(MapPoint.NameForArchiving.Class));
			this.location = location;
		}
		
		this.setName(element.element(NameForArchiving.Name).getText());
		this.setCellImageName(element.element(NameForArchiving.CellImageName).getText());
	}

	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class);
		
		element.addElement(NameForArchiving.Location).add(location.encode());
		
		element.addElement(NameForArchiving.Name).addText(name);
		element.addElement(NameForArchiving.CellImageName).addText(cellImageName);
		
		return element;
	}
	
	
}














