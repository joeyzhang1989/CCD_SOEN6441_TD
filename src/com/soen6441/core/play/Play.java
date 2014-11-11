package com.soen6441.core.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;
import com.soen6441.core.critter.CritterWave;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;

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
 * @author Mohammad Ali
 *
 * 
 * @version $Revision: 1.0 $
 */

public class Play extends Observable  implements IArchive {
	
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
	 * @see #getCoins() 
	 */
	public void spendCoins(int coins){
		this.setCoins(this.getCoins() - coins);
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
	
	private List<CritterWave> critterWaves;
	private int currentWaveIndex;
	
	public List<CritterWave> getCritterWaves() {
		return critterWaves;
	}

	public void setCritterWaves(List<CritterWave> critterWaves) {
		this.critterWaves = critterWaves;
	}

	public int getCurrentWaveIndex() {
		return currentWaveIndex;
	}

	public int getCritterWaveAmount() {
		return critterWaves.size();
	}
	public CritterWave currentWave() {
		return critterWaves.get(currentWaveIndex);
	}
	public CritterWave nextWave() {
		return critterWaves.get(currentWaveIndex+1);
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
		
		MapPoint p5 = new MapPoint(2, 2);
		TowerManager manager1 = TowerManagerFactory.defaultFactory().getManager("BottleTower");
		Tower t1 = manager1.createTower();
		map.setItem(t1, p5);
		
		MapPoint p6 = new MapPoint(5, 0);
		TowerManager manager2 = TowerManagerFactory.defaultFactory().getManager("IceTower");
		Tower t2 = manager2.createTower();
		/*manager2.upgrade(t2); */t2.upgrade();
		map.setItem(t2, p6);
		
		MapPoint p1 = new MapPoint(1, 1);
		MapPoint p2 = new MapPoint(4, 1);
		MapPoint p3 = new MapPoint(4, 3);
		MapPoint p4 = new MapPoint(2, 3);
		
//		Road r1 = new Road(Road.Type.START);
//		Road r2 = new Road();
//		Road r3 = new Road();
//		Road r4 = new Road(Road.Type.END);
//		map.setItem(r1, p1);
//		map.setItem(r2, p2);
//		map.setItem(r3, p3);
//		map.setItem(r4, p4);
		
		List<MapPoint> starts = new ArrayList<MapPoint>();
		starts.add(p1);
		map.setStartPoints(starts);

		List<MapPoint> ends = new ArrayList<MapPoint>();
		ends.add(p4);
		map.setEndPoints(ends);
		
		MapPath path = new MapPath();
		path.addLocation(p1);
		path.addLocation(p2);
		path.addLocation(p3);
		path.addLocation(p4);

		List<MapPath> paths = new ArrayList<MapPath>();
		paths.add(path);
		map.setPaths(paths);
		
		map.getPathManager().generateRoadItemsFromPaths();
	}

	/*
	 * Mark - Archive - Methods
	 */
	
	public class NameForArchiving {
		public static final String Class = "Play";
		public static final String COINS = "coins";
		public static final String MAP = "map";

	}

	@Override
	public void decode(Element element) {
		
		// setting the # of coins
		Node coinNode = element.selectSingleNode(NameForArchiving.COINS);
		this.coins = Integer.parseInt(coinNode.getText());
		
		// reading the map node to access GridMap.
		Node mapNode = element.selectSingleNode(NameForArchiving.MAP);
		Element gridMapNode = (Element) mapNode.selectSingleNode(GridMap.NameForArchiving.Class);

		GridMap map = new GridMap();
		map.decode(gridMapNode);

		this.map = map;
	}

	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class);

		//adding coin Node and setting value to it
		Element coins = element.addElement(NameForArchiving.COINS);
		coins.addText(String.valueOf(this.coins));
		
		//adding map Node and then assigning GridMap to it.
		Element mapElement = element.addElement(NameForArchiving.MAP);
		mapElement.add(this.getMap().encode());

		return element;
	}
	
}
