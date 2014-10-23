package com.soen6441.core.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.map.Road;
import com.soen6441.ui.scene.PlayingScene;

/**
 * 
 * 
 * <h1>Introduction</h1>
 * <p>Play is the like the center data and logic of the game.</p>
 * <p>Play contains {@link GridMap} and some shared information like {@link #coins} and {@link #life}</p>
 * 
 * <br></br>
 * <h1>Tasks</h1>
 * 
 * <h3>Tasks - Using the object</h3>
 * 
 * <h5>General Idea</h5>
 * <p>Play is using the singleton design pattern. </p>
 * <p>The {@link Play} is kind of semi single.
 *  which means once the instance has been created, it can be destroyed and recreated. 
 *  But there will be only zero / one object at runtime </p>
 * 
 * <h5>To get the play object</h5>
 * <p>Use {@link Play#currentPlay()} method to get the instance.</p>
 * 
 * <h5>To destroy the play object</h5>
 * <p>When a play is finished, e.g. you quit from {@link PlayingScene} you should destroy the {@link Play} object.</p>
 * <p>Use {@link Play#destroy()} method to destroy the shared instance, so when someone call {@link Play#currentPlay()} later, they will get a new one.</p>
 * 
 * <br></br>
 * <h3>Tasks - Managing the coins</h3>
 * 
 * <h5>Getting and Setting the coins</h5>
 * <p>Use {@link #getCoins()} and {@link #setCoins(int)} to get and set the coins directly.</p>
 * <p>If you want to increase or decrease the {@link #coins} base on the logic, please use the following methods.</p>
 * 
 * <h5>To change the coins</h5>
 * <p>Use {@link #earnCoins(int)} to increase the coins.</p>
 * <p>Use {@link #spendCoins(int)} to decrease the coins.</p>
 * <p><strong>Attention:</strong> Call {@link #getCoins()} before call {@link #spendCoins(int)} to check whether the coins is enough to spend</p>
 * 
 * <br></br>
 * <h3>Tasks - Being Observed</h3>
 * 
 * <h5>General Idea</h5>
 * <p>This class has been adapted to the Observer Design Pattern.</p>
 * <p>Some of the information could be observed by Observers.</p>
 * <p>When it notifies observers, a extra object will be sent to indicate which information has been changed.</p>
 * 
 * <h5>Details</h5>
 * <p>Observers need to compare the second parameter in the {@link Observer#update(Observable, Object)} to the values below to know the detail.</p>
 * <p>{@link #OBSERVABLE_EVENT_PROPERTY_COINS_DID_CHANGE} This indicate that the {@link #coins} has been changed.</p>
 * <p>{@link #OBSERVABLE_EVENT_PROPERTY_LIFE_DID_CHANGE} This indicate that the {@link #life} has been changed.</p>
 * 
 * 
 * 
 * @author Zhe Zhao
 *
 * 
 */

public class Play extends Observable {
	
	/*
	 * Mark - Singleton - Basic
	 */
	
	/**
	 * The private shared instance to make singleton
	 */
	private static Play currentPlay;
	
	/**
	 * Use this method to get the shared instance
	 * @return Play
	 */
	public static Play currentPlay(){
		if (currentPlay == null){
			currentPlay = new Play();
		}
		return currentPlay;
	}
	
	/**
	 * Close the access to the general constructor
	 */
	private Play()
	{
		
	}
	
	/*
	 * Mark - Singleton - Additional
	 */
	
	/**
	 * Use this method to set the shared instance
	 * @param play
	 */
	public static void setPlay(Play play){
		currentPlay = play;
	}
	
	/**
	 * Use this method to set the shared instance to null
	 */
	public static void destroy(){
		currentPlay = null;
	}
	
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private GridMap map;
	
	private int life;
	private int coins;
	
	/*
	 * Mark - Basic - Methods
	 */
	
	/**
	 * Any logic wants to increase the coins, should call this method 
	 * 
	 * 
	 * @param coins Amount of increasing
	 *
	 */
	public void earnCoins(int coins){
		this.setCoins(this.getCoins() + coins);
	}

	
	/**
	 * Any logic wants to decrease the coins, should call this method.
	 * Please call {@code #getCoins()} first to check whether there is enough money to spend
	 * 
	 * @param coins
	 * 
	
	 * 
	 * @see #getCoins() */
	public void spendCoins(int coins){
		this.coins -= coins;
	}
	
	/*
	 * Mark - Basic - Observerable
	 */
	
	public static String OBSERVABLE_EVENT_PROPERTY_COINS_DID_CHANGE = "ObservableEvent_PropertyCoinsDidChange";
	public static String OBSERVABLE_EVENT_PROPERTY_LIFE_DID_CHANGE = "ObservableEvent_PropertyLifeDidChange";

	/*
	 * Mark - Basic - Getters and Setters
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
	 * Method getLife.
	 * @return int
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Method setLife.
	 * @param life int
	 */
	public void setLife(int life) {
		this.life = life;
		
		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_LIFE_DID_CHANGE);
	}

	/**
	 * Method getCoins.
	 * @return int
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Method setCoins.
	 * @param coins int
	 */
	public void setCoins(int coins) {
		this.coins = coins;

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_COINS_DID_CHANGE);
	}
	
	/*
	 * Mark - Debug - Methods
	 */
	
	public void buildDemo(){
		
		this.setCoins(1000);
		this.setLife(10);
		
		map = new GridMap();
		
		map.setWidth(6);
		map.setHeight(4);
		
		MapPoint p1 = new MapPoint(1, 1);
		MapPoint p2 = new MapPoint(2, 1);
		MapPoint p3 = new MapPoint(3, 1);
		MapPoint p4 = new MapPoint(3, 2);
		
		Road r1 = new Road(Road.Type.START);
		Road r2 = new Road();
		Road r3 = new Road();
		Road r4 = new Road(Road.Type.END);
		map.setItem(r1, p1);
		map.setItem(r2, p2);
		map.setItem(r3, p3);
		map.setItem(r4, p4);
		
		List<MapPoint> starts = new ArrayList<MapPoint>();
		starts.add(p1);
		map.setStartPoints(starts);

		List<MapPoint> ends = new ArrayList<MapPoint>();
		ends.add(p4);
		map.setEndPoints(ends);
		
		MapPath path = new MapPath();
		path.addLocation(p1);
		path.addLocation(p3);
		path.addLocation(p4);

		List<MapPath> paths = new ArrayList<MapPath>();
		paths.add(path);
		map.setPaths(paths);
	}
}
