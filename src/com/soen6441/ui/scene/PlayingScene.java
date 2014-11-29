package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import com.soen6441.core.log.Log;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.Road;
import com.soen6441.core.play.Play;
import com.soen6441.core.play.PlayEventListener;
import com.soen6441.core.play.PlayManager;
import com.soen6441.core.strategy.Strategy;
import com.soen6441.core.tower.Tower;
import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;
import com.soen6441.ui.common.Command;
import com.soen6441.ui.common.GridViewSelectionListener;
import com.soen6441.ui.common.IInspectable;
import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;

/**
 * 
 * 
 * this is PlayingScene class is responsible of the playscene view and showing the 
 * corresponding information of the related models
 * not finish with the inner classes
 * 
 * @author Chenglong Zhang 
 * 
 * @version $Revision: 1.0 $
 */
public class PlayingScene extends View implements Observer, GridViewSelectionListener, PlayEventListener{
	
	/*
	 * Mark - Context - Properties
	 */

	private Play play;
	
	/*
	 * Mark - Context - Observer
	 */
	
	/**
	 * Method update.
	 * @param o Observable
	 * @param arg Object
	 * @see java.util.Observer#update(Observable, Object)
     */
	@Override
	public void update(Observable o, Object arg) {
		if (o == play) {
			if (arg == Play.OBSERVABLE_EVENT_PROPERTY_COINS_DID_CHANGE){
				coinsLabel.setText(Integer.toString(play.getCoins()));
			}
			if (arg == Play.OBSERVABLE_EVENT_PROPERTY_LIFE_DID_CHANGE){
				lifelabel.setText(Integer.toString(play.getLife()));
			}
		}
	}
	
	/*
	 * Mark - View - Properties
	 */
	
	//bannerView 
	private Button controlButton;
	private Label infoLabel;
	private Label money;
	private Label coinsLabel;
	private Label life;
	private Label lifelabel;
   
	//mapView
	private MapView mapView;
	
	//inspectorView
	private InspectorView inspectorView;
	
	//bottomView
	private Button backButton;
	private Button saveButton;
	
	/*
	 * Mark - View - Life Cycle
	 */
	
	/**
	 * override the method init in the super class View
	 * to initialize the initial View in the Playscene and
	 * get the play instance (singleton)addObserver to the observerable play
	 */
	protected void init() {
		super.init();
		play = Play.currentPlay();
		play.addObserver(this);
		play.setEventListener(this);
		
		Log log = new Log(Log.CATEGORY_MAP).message("Played");
		play.addLogToMapFile(log);
	};
	
	/**
	 * override the method initSubviews in the super class View
	 * to initialize the PlayingScene UI
	 */
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		this.setBackground(new Color(0x242424));
		
		//initial the bannnerView 
		View bannerView = new View();
		bannerView.setLocation(0, 0);
		bannerView.setSize(800, 60);
		bannerView.setBackground(new Color(0x000000));
		this.add(bannerView);
		
		// add the controlButton to the play Scene bannerview
		
		controlButton = new Button();
		controlButton.setTitle("Next Wave");
		// set button location to the interface 
		controlButton.setSize(120, 40);
		controlButton.setLocation(10, 10);
		bannerView.add(controlButton);
		
		// this label should be updated 
		infoLabel = new Label();
		infoLabel.setSize(120, 40);
		infoLabel.setLocation(140, 10);
		bannerView.add(infoLabel);
		updateInfoLabel();
		
		//coinslabel
		money = new Label();
		money.setText("Money");
		money.setSize(120, 40);
		money.setLocation(400, 10);
		//money.setText(""+play.getCoins());
		bannerView.add(money);   
		
	
		//label to show the coins change
		coinsLabel = new Label();
		coinsLabel.setSize(80,40);
		coinsLabel.setLocation(520,10);
		coinsLabel.setText(Integer.toString(play.getCoins()));
		bannerView.add(coinsLabel);
		
		//lifelabel
		life = new Label();
		life.setText("Life");
		life.setSize(120, 40);
		life.setLocation(640, 10);
		bannerView.add(life);   
		
		//lifelable to store the life change
		lifelabel = new Label();
		lifelabel.setSize(80,40);
		lifelabel.setText(Integer.toString(play.getLife()));
		lifelabel.setLocation(720,10);
		bannerView.add(lifelabel);
		
		//create the bottomView to the PlayingScene
		View bottomView = new View();
		bottomView.setLocation(0, 540);
		bottomView.setSize(800, 40);
		bottomView.setBackground(new Color(0x000000));
		PlayingScene.this.add(bottomView);
		
		//Quit button
		backButton = new Button();
		backButton.setTitle("Quit");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 10);
		bottomView.add(backButton);
		
		//Save button
		saveButton = new Button();
		saveButton.setTitle("Save");
		saveButton.setSize(60, 20);
		saveButton.setLocation(720, 10);
		bottomView.add(saveButton);
		
		//mapView
		/* 
		 * get the map information from the mapView and set the 
		 * map to the center of GridmapView
		 */
		mapView = new MapView();
		mapView.setMap(play.getMap());
	    mapView.setSize(this.mapView.suggestedSize());
	    int x = (800 - 180 - this.mapView.suggestedSize().width) / 2;
		int y = (600 - this.mapView.suggestedSize().height) / 2;
		mapView.setLocation(x, y);
		this.add(mapView);
		
		//inspectorView
		inspectorView  = new InspectorView();
		inspectorView.setLocation(620, 60);
		inspectorView.setSize(180,480);
		inspectorView.setBackground(new Color(0x111111));
		this.add(inspectorView);
		
	}	

	@Override
	protected void initEvents() {
		super.initEvents();
		
		//perform the nextWave function to create the next wave of critters
		
		controlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play.nextWave();
				updateInfoLabel();
			}
		});
		
		/**
		 * perform the exit function to go back to the main scene
		 * 
		 */
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
		/**
		 * perform the save function to save the playing Game
		 * 
		 */
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		mapView.setSelectionListener(this);
		play.registeRunner(mapView);
		play.startRunner();
	}
	
	private void updateInfoLabel(){
		infoLabel.setText("Wave: " + (play.getCurrentWaveIndex() + 1) + "/"  + play.getCritterWaveAmount());
	}
	
	private void save() {
		PlayManager playManager = new PlayManager();
		playManager.save(play.getSourceFile(), play);
		JOptionPane.showMessageDialog(this, "Save Successfull");
	}

	private void back() {
		play.stopRunner();
		play.deleteObserver(PlayingScene.this);
		Play.destroy();
		 
		PlayingScene.this.viewFlow.pop();
		
	}
	/*
	 * Mark - Play Events - Methods
	 */
	

	/**
	 * Method playWaveDidStart.
	 * @param play Play
	 * @see com.soen6441.core.play.PlayEventListener#playWaveDidStart(Play)
	 */
	@Override
	public void playWaveDidStart(Play play) {
		controlButton.setEnabled(false);
		saveButton.setEnabled(false);

		Log log = new Log(Log.CATEGORY_WAVE).message("Started @" + play.getCurrentWaveIndex());
		play.getLogger().addLog(log);
	}

	/**
	 * Method playWaveDidSendAllCritter.
	 * @param play Play
	 * @see com.soen6441.core.play.PlayEventListener#playWaveDidSendAllCritter(Play)
	 */
	@Override
	public void playWaveDidSendAllCritter(Play play) {

		Log log = new Log(Log.CATEGORY_WAVE).message("All Critters are Sent @" + play.getCurrentWaveIndex());
		play.getLogger().addLog(log);
	}

	/**
	 * Method playAllCrittersDidKilled.
	 * @param play Play
	 * @see com.soen6441.core.play.PlayEventListener#playAllCrittersDidKilled(Play)
	 */
	@Override
	public void playAllCrittersDidKilled(Play play) {
		controlButton.setEnabled(true);
		saveButton.setEnabled(true);

	}

	/**
	 * Method playGameover.
	 * @param play Play
	 * @see com.soen6441.core.play.PlayEventListener#playGameover(Play)
	 */
	@Override
	public void playGameover(Play play) {

		Log log = new Log(Log.CATEGORY_MAP).message("Lost");
		play.addLogToMapFile(log);
		
		JOptionPane.showMessageDialog(this, "Gameover");
		back();
	}

	/**
	 * Method playSuccess.
	 * @param play Play
	 * @see com.soen6441.core.play.PlayEventListener#playSuccess(Play)
	 */
	@Override
	public void playSuccess(Play play) {

		Log log = new Log(Log.CATEGORY_MAP).message("Won").value(Integer.toString(play.getScore()));
		play.addLogToMapFile(log);
		
		JOptionPane.showMessageDialog(this, "Success");
		back();
	}
	 
	
	/*
	 * Mark - Inspection - Methods
	 */

	/**
	 * Method gridViewDidSelect.
	 * @see com.soen6441.ui.common.GridViewSelectionListener#gridViewDidSelect()
	 */
	@Override
	public void gridViewDidSelect() {
		MapItem mapItem = play.getMap().getSelectedItem();
		
		if (mapItem == null){
			inspectorView.setOn(new InspectableScenery());
			inspectorView.update();
			
		} else if (mapItem instanceof Road){
			Road road = (Road)mapItem;
			inspectorView.setOn(new InspectableRoad(road));
			inspectorView.update();
			
		} else if (mapItem instanceof Tower){
			Tower tower = (Tower) mapItem;
			inspectorView.setOn(new InspectableTower(tower));
			inspectorView.update();
		}
	}
		
	
	/*
	 * Mark - Inspection - Classes - Scenery
	 */
	
	/**
	 *
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a empty space scene that the inspector view should update 
	 * 
	 * @author Chenglong Zhang 
	 * @version $Revision: 1.0 $
	 */
	private class InspectableScenery implements IInspectable
	{
		private List<Command> buildCommands;
		private TowerManagerFactory towerManagerFactory;

		public InspectableScenery() {
			buildCommands = new ArrayList<Command>();
			
			towerManagerFactory = TowerManagerFactory.defaultFactory();
			List<String> typeNames = towerManagerFactory.getTypeNames();
			for (String typeName : typeNames) {
				TowerManager towerManager = towerManagerFactory.getManager(typeName);
				Command command = new Command();
				command.setTitle("Buy " + towerManager.getLeveledTowers().get(0).getName());
				command.setSubtitle(towerManager.getInitialPrice() + "$");
				buildCommands.add(command);
			}
		}

		/**
		 * Method title.			
		 * @return String
         * @see com.soen6441.ui.common.IInspectable#title()
         */
		@Override
		public String title() {
			return "Scenery";
		}

		/**
		 * Method subtitle.
		 * @return String
         * @see com.soen6441.ui.common.IInspectable#subtitle()
         */
		@Override
		public String subtitle() {
			return "This is a empty space.";
		}

		/**
		 * Method description.
		 * @return String
         * @see com.soen6441.ui.common.IInspectable#description() 
         */ 

		@Override
		public String description() {
		
			return "You can select any type of tower to build on this cell.";
		}

		/**
		 * Method gridCommands.
		 * @return List<Command>
		 * @see com.soen6441.ui.common.IInspectable#gridCommands()
		 */
		@Override
		public List<Command> gridCommands() {
			return null;
		}
		
		/**
		 * Method commands.
		 *
         * @return List<Command> 
         * @see com.soen6441.ui.common.IInspectable#commands()
         */
		@Override
		public List<Command> commands() {
			return buildCommands;
		}

		/**
		 * Method execute.
		 * @param command Command
		 * @see com.soen6441.ui.common.IInspectable#execute(Command)
         */
		@Override
		public void execute(Command command) {
			int index = buildCommands.indexOf(command);
			String typeName = towerManagerFactory.getTypeNames().get(index);
			TowerManager towerManager = towerManagerFactory.getManager(typeName);
			int price = towerManager.getInitialPrice();
			if (play.getCoins() >= price) {
				play.spendCoins(price);
				
				Tower tower = towerManager.createTower();
				GridMap gridMap = play.getMap();
				play.getMap().setItem(tower, gridMap.getSelectedPoint());
				
				Log log = new Log(Log.CATEGORY_TOWER).identity(tower.getIdentity()).message("Created");
				play.getLogger().addLog(log);
			}
		}
	}
	

	/*
	 * Mark - Inspection - Classes - Tower
	 */
	
	/**
	 * 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting tower scene the inspector view should update 
	 * 
	 * @author Chenglong Zhang 
	 * @version $Revision: 1.0 $
	 */
	
	private class InspectableTower implements IInspectable
	{
		 
		/**
		 * Method title.
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		private Command upgradeCommand;
		private Command refundCommand;
		private Tower tower;
		
		private List<Command> strategyCommands;
		
		/**
		 * Constructor for InspectableTower.
		 * @param tower Tower
		 */
		public InspectableTower(Tower tower) {
		
			this.tower = tower;
		}
		/**
		 * Method title.
		 * @return String 
         * @see com.soen6441.ui.common.IInspectable#title()
         */
		@Override
		public String title() {
			
			return tower.getName();
		}

		/**
		 * Method subtitle.
		 * @return String  
         * @see com.soen6441.ui.common.IInspectable#subtitle()  
         * @see com.soen6441.ui.common.IInspectable#subtitle()
		 */
		@Override
		public String subtitle() {
			
			return  "Level " + tower.getLevel();
			
		}

		/**
		 * Method description.
		 * @return String
         * @see com.soen6441.ui.common.IInspectable#description()
         */
		@Override
		public String description() {
			
			return tower.getDescription() + "\n" + tower.getDetailInformation();
		}
		
		/**
		 * Method gridCommands.
		 * @return List<Command>
		 * @see com.soen6441.ui.common.IInspectable#gridCommands()
		 */
		@Override
		public List<Command> gridCommands() {
			if (tower.isStrategyEnabled()) {
				strategyCommands = new ArrayList<Command>();
				List<Strategy> strategies = Strategy.getAllStrategies();
				for (Strategy strategy : strategies) {
					Command command = new Command();
					command.setImageName(strategy.getImageName());
					strategyCommands.add(command);
				}
				return strategyCommands;
			} else {
				return null;
			}
		}

		/**
		 * Method commands.
		 * @return List<Command>
         * @see com.soen6441.ui.common.IInspectable#commands()
         */
		@Override
		public List<Command> commands() {
			List<Command> commands = new ArrayList<Command>();
			if (tower.canUpgrade()) {
				upgradeCommand = new Command("Upgrade Tower", tower.getUpgradePrice() + "$");
				commands.add (upgradeCommand);
			} 
			
			refundCommand = new Command("Refund Tower", tower.getSellPrice() + "$");
			commands.add (refundCommand);
			
			return commands;
		}

		/**
		 * Method execute.
		 * @param command Command
		 * @see com.soen6441.ui.common.IInspectable#execute(Command)
         */
		@Override
		public void execute(Command command) {
			if(command == upgradeCommand){
				int price = play.getCoins();
				if (price >= tower.getUpgradePrice()) {
					play.spendCoins(tower.getUpgradePrice());
					tower.upgrade();
					inspectorView.update();
					
					Log log = new Log(Log.CATEGORY_TOWER).identity(tower.getIdentity()).message("Upgraded");
					play.getLogger().addLog(log);
				}	
			} else if (command == refundCommand) {
				play.earnCoins(tower.getSellPrice());
				play.getMap().removeItem(tower);
				
				Log log = new Log(Log.CATEGORY_TOWER).identity(tower.getIdentity()).message("Refunded");
				play.getLogger().addLog(log);
			} else {
				int index = strategyCommands.indexOf(command);
				String strategyName = Strategy.getStrategyNames().get(index);
				tower.setStrategyName(strategyName);
			}
		}	
	}	


	/*
	 * Mark - Inspection - Classes - Road
	 */


	/**
	 *
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a road scene that the inspector view should update 
	 * 
	 * @author Chenglong Zhang 
	 * @version $Revision: 1.0 $
	 */
	private class InspectableRoad implements IInspectable
	{
		private Road road;
		
		/**
		 * Constructor for InspectableRoad.
		 * @param road Road
		 */
		public InspectableRoad(Road road){
			this.road = road;
		}
		/**
		 * Method title. check the selected road type to update the inspectorView Title
		 * @return String  * @see com.soen6441.ui.common.IInspectable#title()  * @see com.soen6441.ui.common.IInspectable#title()
		 */
		@Override
		public String title() {
			return road.getName();
		}
	
		/**
		 * Method subtitle.
		 * @return String
         * @see com.soen6441.ui.common.IInspectable#subtitle()
         */
		@Override
		public String subtitle() {
		
			return "This is a road";
		}

		/**
		 * Method description.
		 * @return String  * @see com.soen6441.ui.common.IInspectable#description()  * @see com.soen6441.ui.common.IInspectable#description()
		 */
		@Override
		public String description() {
		
			return null;
		}

		/**
		 * Method gridCommands.
		 * @return List<Command>
		 * @see com.soen6441.ui.common.IInspectable#gridCommands()
		 */
		@Override
		public List<Command> gridCommands() {
			return null;
		}
		
		/**
		 * Method commands.
		 * @return List<Command>
         * @see com.soen6441.ui.common.IInspectable#commands()
         */
		@Override
		public List<Command> commands() {
		
			return null;
		}

		/**
		 * Method execute.
		 * @param command Command
		 * @see com.soen6441.ui.common.IInspectable#execute(Command)
         */
		@Override
		public void execute(Command command) {
		
		}
	
	}



}
