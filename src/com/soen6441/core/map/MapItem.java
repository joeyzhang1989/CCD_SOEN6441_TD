package com.soen6441.core.map;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;
import com.soen6441.core.effect.Effect;
import com.soen6441.core.play.Play;


/**
 * This class will define the distance the object has to travel along the path in the Map.  
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali

 * @version $Revision: 1.0 $
 */

public class MapItem extends Observable implements IArchive{
	
	/*
	 * Mark - Context - Properties
	 */

	protected Play play;
	protected GridMap map;
	
	/*
	 * Mark - Context - Getters & Setters
	 */

	/**
	 * Method getPlay.
	 * @return Play
	 */
	public Play getPlay() {
		return play;
	}

	/**
	 * Method setPlay.
	 * @param play Play
	 */
	public void setPlay(Play play) {
		this.play = play;
	}
	
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
	 * Mark - Basic - Properties
	 */

	private MapPoint location;
	
	/*
	 * Mark - Basic - Getters & Setters
	 */


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
	 * Mark - Effect - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_EFFECTS_DID_CHANGE = "ObservableEvent_PropertyEffectsDidChange";
	
	/*
	 * Mark - Effect - Properties
	 */

	private Map<String , Effect> effects = new HashMap<String, Effect>();
	private Object effectLock = new Object();
	
	/*
	 * Mark - Effect - Methods
	 */
	private void updateAffectableValues(){
		this.resetAffectableValue();
		for(int i=0; i< this.getAllEffects().size(); i++) {
			this.getAllEffects().get(i).affect();
		}
	}
	
	protected void resetAffectableValue() {
		
	}
   
	/**
     * Method addEffect.
     * @param effect Effect
     * @see com.soen6441.core.effect.IAffectable#addEffect(Effect)
     */
	public void addEffect(Effect effect) {
		
		Effect comparedEffect = this.getEffect(effect.getType());
		
		if (comparedEffect != null) {
			if(effect.strongerThan(comparedEffect)) {
				this.removeEffect(effect);
				effect.setOn(this);
				effects.put(effect.getType(), effect);
				effect.affect();
				effect.start();
			}
		} else {
			effect.setOn(this);
			effects.put(effect.getType(), effect);
			effect.affect();
			effect.start();	
		}
		
		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_EFFECTS_DID_CHANGE);
	}


	/**
	 * Method getEffect.
	 * @param type String
     * @return Effect
     * @see com.soen6441.core.effect.IAffectable#getEffect(String)
     */
	public Effect getEffect(String type) {
		
		return effects.get(type);
	}
	
	/**
	 * Method getAllEffects.
	 * @return List<Effect>
	 */
	public List<Effect> getAllEffects() {
		return new ArrayList<Effect>(effects.values());
	}


	/**
	 * Method removeEffect.
	 * @param effect Effect
	
     * @see com.soen6441.core.effect.IAffectable#removeEffect(Effect) */
	public void removeEffect(Effect effect) {
		effect.stop();
		effect.setOn(null);
		effects.remove(effect.getType());
		this.updateAffectableValues();

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_EFFECTS_DID_CHANGE);
	}

	
	/*
	 * Mark - Archive - Methods
	 */
	 
	/**
     * @author Zhe Zhao
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving{
		public static final String Class = "MapItem";
		private static final String Location = "location";
		private static final String Name = "name";
		private static final String CellImageName = "cellImageName";
	}

	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
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

	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class);
		
		element.addElement(NameForArchiving.Location).add(location.encode());
		
		element.addElement(NameForArchiving.Name).addText(name);
		
		element.addElement(NameForArchiving.CellImageName).addText(cellImageName);
		
		return element;
	}
	
	
}














