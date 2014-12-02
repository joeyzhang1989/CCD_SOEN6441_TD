package com.soen6441.core.effect;

import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.map.MapItem;

/**
 * 
 * Any spatial thing which will temporarily change the <code>AffectableValue</code> should use this class to make it happen.
 * 
 * @see AffectableValue
 *
 * @author Zhe Zhao
 * @author Chenglong Zhang
 * 
 * @version $Revision: 1.0 $
 */
public class Effect implements TimerListener {
	
	/*
	 * Properties
	 */
	
	protected String type;
	private MapItem on;
	private Timer timer;
	
	
	/*
	 * Constructors
	 */
	
	public Effect() {
		timer = new Timer();
		timer.setTimerListener(this);
	}

	/**
	 * Constructor for Effect.
	 * @param effect Effect
	 * @return boolean
	 */
	/*
	public Effect(IAffectable on) {
		super();
		this.on = on;
	}
	*/

	/*
	 * Methods
	 */
	
	/**
	 * Test if this effect is stronger to another effect.
	 * The another effect should be same type as this effect in order to compare.
	 * Use <code>getType</code> before calling this method.
	 * 
	 * @param effect Another effect, which should be same type as this one.
	 * @return <code>true</code> if this effect is stronger, 
	 * 			<code>false</code> if the effect in the para is stronger or they are imcomparable. 
	 * 
	 * @see #getType() 
	 */
	public boolean strongerThan(Effect effect){
		
		return false;
	}
	
	/**
	 * To start the effect
	 * This method should be called after putting this object to the affectable object.
	 */
	public void affect(){
		
	}

	public void start() {
		
	}
	
	public void stop() {
		
	}
	
	/**
	 * Method timerTick.
	 * @param timer Timer
	 * @see com.soen6441.core.TimerListener#timerTick(Timer)
	 */	@Override
	public void timerTick(Timer timer) {
		
	}
	
	/*
	 * Getters and Setters
	 */
	
	/**
	 * Method getType.
     * @return String  
     */
	public String getType() {
		return type;
	}
	
	/**
	 * Method getOn.
     * @param type String
	 */
	

	public void setType(String type) {
		this.type = type;
	}
	
	private String cellImageName;

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

	/**
	 * Method getOn.
	 * @return MapItem
	 */
	public MapItem getOn() {
		return on;
	}

	/**
	 * Method setOn.
	 * @param on MapItem
	 */
	public void setOn(MapItem on) {
		this.on = on;
	}

	/**
	 * Method getTimer.
	 * @return Timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * Method setTimer.
	 * @param timer Timer
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	
	
		
}
