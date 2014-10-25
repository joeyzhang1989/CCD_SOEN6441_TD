package com.soen6441.core.map;


/**
 * This class will represent Road which is a MapItem. It extends MapItem and also defines additional methods 
 * specific to it. 
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 * @version $Revision: 1.0 $
 */

public class Road extends MapItem {
	
	public static String OBSERVABLE_EVENT_PROPERTY_TYPE_DID_CHANGE = "ObservableEvent_PropertyTypeDidChange";
	
	/**
	 */
	public enum Type{
		NORMAL, START, END
	}
	
	private Type type;

	public Road() {
		super();
		this.type = Type.NORMAL;
	}
	
	/**
	 * Constructor for Road.
	 * @param type Type
	 */
	public Road(Type type) {
		super();
		this.type = type;
	}
	
	/**
	 * Method getType.
	 * @return Type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Method setType.
	 * @param type Type
	 */
	public void setType(Type type) {
		this.type = type;

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_TYPE_DID_CHANGE);
	}
	
	/**
	 * Method getName.
	 * @return String
	 */
	@Override
	public String getName() {
		switch (type) {
		case NORMAL:
			return "Road";
		case START:
			return "Entrance";
		case END:
			return "Exit";
		default:
			return null;
		}
	}
 
	/**
	 * Method getCellImageName.
	 * @return String
	 */
	@Override
	public String getCellImageName() {

		switch (type) {
		case NORMAL:
			return "road_cell_normal.png";
		case START:
			return "road_cell_start.png";
		case END:
			return "road_cell_end.png";
		default:
			return null;
		}
	}
	
	
}
