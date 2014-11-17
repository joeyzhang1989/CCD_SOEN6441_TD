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
	
	/*
	 * Mark - Constructors
	 */
	
	public Road() {
		super();
		this.roadType = RoadType.NORMAL;
	}
	
	/**
	 * Constructor for Road.
	 * @param roadType Type
	 */
	public Road(RoadType roadType) {
		super();
		this.roadType = roadType;
	}
	
	
	/*
	 * Mark - Basic - Properties
	 */
	
	/**
     * @author Mohammad Ali
	 */
	public enum RoadType{
		NORMAL, START, END
	}
	
	private RoadType roadType;

	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_TYPE_DID_CHANGE = "ObservableEvent_PropertyTypeDidChange";
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	
	/**
	 * Method getType.
	 * @return Type
     */
	public RoadType getRoadType() {
		return roadType;
	}

	/**
	 * Method setType.
	 * @param roadType Type
	 */
	public void setRoadType(RoadType roadType) {
		this.roadType = roadType;

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_TYPE_DID_CHANGE);
	}
	
	/*
	 * Mark - Display - Methods
	 */
	
	/**
	 * Method getName.
	 * @return String
     */
	@Override
	public String getName() {
		switch (roadType) {
		case NORMAL:
			return "Road";
		case START:
			return "Entry Point";
		case END:
			return "Exit Point";
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

		switch (roadType) {
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
