package com.soen6441.core.play;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;
import com.soen6441.core.Timer;
import com.soen6441.core.TimerListener;
import com.soen6441.core.critter.Critter;
import com.soen6441.core.critter.CritterWave;
import com.soen6441.core.log.Log;
import com.soen6441.core.log.Logger;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;
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
 * @author Mohammad Ali
 *
 * 
 * @version $Revision: 1.0 $
 */

public class Play extends Observable implements IArchive, TimerListener{
	
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
	public Play()
	{
		initRunner();
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
	private int score;
	
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
		
		Log log = new Log(Log.CATEGORY_GAME).message("Money Earned").value(Integer.toString(coins));
		logger.addLog(log);
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

		Log log = new Log(Log.CATEGORY_GAME).message("Money Lost").value(Integer.toString(coins));
		logger.addLog(log);
	}
	
	/**
	 * Method alterLife.
	 * @param life int
	 */
	public void alterLife(int life) {
		this.setLife(this.getLife() + life);

		Log log = new Log(Log.CATEGORY_GAME).message("Life Changed").value(Integer.toString(life));
		logger.addLog(log);
	}
	
	public void alterScore(int score) {
		this.setScore(this.getScore() + score);
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
		if (life < 0) {
			life = 0;
		}
		
		this.life = life;
		
		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_LIFE_DID_CHANGE);
		
		if (this.life == 0) {
			gameover();
		}
	}

	/**
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
		if (coins < 0) {
			coins = 0;
		}
		
		this.coins = coins;

		this.setChanged();
		this.notifyObservers(OBSERVABLE_EVENT_PROPERTY_COINS_DID_CHANGE);
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	/*
	 * Mark - Critter Waves - Properties
	 */
	 
	private List<CritterWave> critterWaves;
	private int currentWaveIndex = -1;
	
	private int critterState = _CRITTER_STATE_WAIT;
	private final static int _CRITTER_STATE_WAIT = 0;
	private final static int _CRITTER_STATE_PRODUCING = 1;
	private final static int _CRITTER_STATE_PRODUCED = 2;
	
	
	private Timer waveTimer;
	
	/**
	 * Method getCritterWaves.
	 * @return List<CritterWave>
	 */
	public List<CritterWave> getCritterWaves() {
		return critterWaves;
	}
	
	/*
	 * Mark - Critter Waves - Methods
	 */
	/**
	 * Method currentWave.
	 * @return CritterWave
	 */
	private CritterWave currentWave(){
		return critterWaves.get(currentWaveIndex);
	}
	
	public void nextWave() {
		critterState = _CRITTER_STATE_PRODUCING;
		
		currentWaveIndex ++;
		CritterWave currentWave = currentWave();
		
		waveTimer = new Timer();
		waveTimer.setRepeats(true);
		waveTimer.setRepeatTimes(currentWave.amount());
		waveTimer.setTimeIntervalSecond(currentWave.getTimeGap());
		waveTimer.setTimerListener(this);
		
		waveTimer.startImmediately();
		
		if (eventListener != null) {
			eventListener.playWaveDidStart(currentPlay);
		}
	}

	private void waveTimerTick(){
		Critter critter = currentWave().nextCritter();
		MapPoint startLocation = map.getStartPoints().get(0).copy();
		map.addCritter(critter, startLocation);
		
		if (currentWave().getCurrentIndex() == currentWave().amount() - 1) {
			critterState = _CRITTER_STATE_PRODUCED;
		}
	}
	
	/**
	 * Method getCritterWaveAmount.
	 * @return int
	 */
	public int getCritterWaveAmount() {
		return critterWaves.size();
	}
	
	/*
	 * Mark - Critter Waves - Getters & Setters
	 */

	/**
	 * Method setCritterWaves.
	 * @param critterWaves List<CritterWave>
	 */
	public void setCritterWaves(List<CritterWave> critterWaves) {
		this.critterWaves = critterWaves;
	}

	/**
	 * Method getCurrentWaveIndex.
	 * @return int
	 */
	public int getCurrentWaveIndex() {
		return currentWaveIndex;
	}
	
	/*
	 * Mark - Runner - Properties
	 */
	
	private Timer runningTimer;


	public static final double RUNNER_FPS = 32;
	private List<TimerListener> runningListeners;
	
	
	
	
	/*
	 * Mark - Runner - Methods
	 */
	
	private void initRunner(){
		runningListeners = new ArrayList<TimerListener>();
		
		runningTimer = new Timer();
		runningTimer.setTimeIntervalSecond(1 / RUNNER_FPS);
		runningTimer.setRepeats(true);
		runningTimer.setTimerListener(this);
		
	}
	
	/**
	 * Method runningTimerTick.
	 * @param timer Timer
	 */
	private void runningTimerTick(Timer timer){
		if (map.getCritters().size() == 0 && critterState == _CRITTER_STATE_PRODUCED) {
			critterState = _CRITTER_STATE_WAIT;
			if (eventListener != null) {
				eventListener.playAllCrittersDidKilled(currentPlay);
			}
			
			if (this.currentWaveIndex == critterWaves.size() - 1) {
				success();
			}
		}
		
		List<TimerListener> runningListeners = new ArrayList<TimerListener>(this.runningListeners);
		for (TimerListener timerListener: runningListeners) {
			timerListener.timerTick(timer);
		}
	}
	
	/**
	 * Method registeRunner.
	 * @param timerListener TimerListener
	 */
	public void registeRunner(TimerListener timerListener) {
		runningListeners.add(timerListener);
	}
	
	/**
	 * Method resignRunner.
	 * @param timerListener TimerListener
	 */
	public void resignRunner(TimerListener timerListener) {
		runningListeners.remove(timerListener);
	}
	
	public void startRunner() {
		runningTimer.start();
	}
	
	public void stopRunner() {
		runningTimer.stop();
	}
	
	private void gameover() {
		if (eventListener != null) {
			eventListener.playGameover(currentPlay);
		}
		stopRunner();
	}
	
	private void success() {
		if (eventListener != null) {
			eventListener.playSuccess(currentPlay);
		}
		stopRunner();
	}
	
	
	/*
	 * Mark - Runner - Getters & Setters
	 */
	
	/**
	 * Method getRunningTimer.
	 * @return Timer
	 */
	public Timer getRunningTimer() {
		return runningTimer;
	}
	
	/*
	 * Mark - Timer Listener - Methods
	 */
	

	/**
	 * Method timerTick.
	 * @param timer Timer
	 * @see com.soen6441.core.TimerListener#timerTick(Timer)
	 */
	@Override
	public void timerTick(Timer timer) {
		if (timer == runningTimer) {
			runningTimerTick(timer);
		} else if (timer == waveTimer) {
			waveTimerTick();
		}
	}
	
	/*
	 * Mark - Events - Properties
	 */
	
	private PlayEventListener eventListener;
	
	/*
	 * Mark - Events - Getters & Setters
	 */
	 

	/**
	 * Method getEventListener.
	 * @return PlayEventListener
	 */
	public PlayEventListener getEventListener() {
		return eventListener;
	}

	/**
	 * Method setEventListener.
	 * @param eventListener PlayEventListener
	 */
	public void setEventListener(PlayEventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	/*
	 * Mark - Log - Properties
	 */

	private long identity = 0;
	private Logger logger = new Logger();
	
	/*
	 * Mark - Log - Methods
	 */
	 
	public long generateIdentity(){
		identity ++;
		return identity;
	}
	
	public void addLogToMapFile(Log log){
		File mapFile = new File(mapFilePath);
		PlayManager manager = new PlayManager();
		Play originalPlay = manager.read(mapFile);
		originalPlay.getMap().getLogger().addLog(log);
		manager.save(originalPlay.getSourceFile(), originalPlay);
	}

	/*
	 * Mark - Log - Getters & Setters
	 */
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	
	/*
	 * Mark - Archive - Methods
	 */


	/**
	 * @author Zhe Zhao
	 */
	public class NameForArchiving {
		public static final String Class = "Play";
		public static final String MapFilePath = "mapFilePath";
		public static final String Coins = "coins";
		public static final String Life = "life";
		public static final String Score = "score";
		
		public static final String CurrentWaveIndex = "currentWaveIndex";
		public static final String Map = "map";
		public static final String Identity = "identity";
		public static final String Logger = "logger";
		

	}

	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		this.mapFilePath = element.elementText(NameForArchiving.MapFilePath);
		
		this.coins = Integer.parseInt(element.elementText(NameForArchiving.Coins));
		this.life = Integer.parseInt(element.elementText(NameForArchiving.Life));
		this.score = Integer.parseInt(element.elementText(NameForArchiving.Score));
		this.currentWaveIndex = Integer.parseInt(element.elementText(NameForArchiving.CurrentWaveIndex));
		
		Element mapElement = element.element(NameForArchiving.Map).element(GridMap.NameForArchiving.Class);
		GridMap map = new GridMap();
		map.setPlay(this);
		map.decode(mapElement);
		this.map = map;
		
		this.identity = Integer.parseInt(element.elementText(NameForArchiving.Identity));
		
		Element loggerElement = element.element(NameForArchiving.Logger).element(Logger.NameForArchiving.Class);
		Logger logger = new Logger();
		logger.decode(loggerElement);
		this.logger = logger;
	}

	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class);


		return element;
	}
	
	/*
	 * Mark - File - Properties
	 */
	 
	private File sourceFile;
	private String mapFilePath;
	
	/*
	 * Mark - File - Getters & Setters
	 */
	 
	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	public String getMapFilePath() {
		return mapFilePath;
	}

	public void setMapFilePath(String mapFilePath) {
		this.mapFilePath = mapFilePath;
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
	
}
