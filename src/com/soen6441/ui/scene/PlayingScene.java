package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.Road;
import com.soen6441.core.play.Play;
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
public class PlayingScene extends View implements Observer, GridViewSelectionListener{
	
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
	
	/*
	 * Mark - View - Life Cycle
	 */
	
	/**
	 * override the method init in the super class View
	 * to initialize the initial View in the Playscene and
	 * get the play instance (singleton)addObserver to the observerable play
	 */
	@Override
	protected void init() {
		play = Play.currentPlay();
		play.addObserver(this);
		super.init();
		
	}
	
	/**
	 * override the method initSubviews in the super class View
	 * to initialize the PlayingScene UI
	 */
	@Override
	protected void initSubviews() {
		super.initSubviews();
		//initial the bannnerView 
		View bannerView = new View();
		bannerView.setLocation(0, 0);
		bannerView.setSize(800, 60);
		this.add(bannerView);
		
		// add the controlButton to the play Scene bannerview
		
		this.setBackground(new Color(0xDDDDDD));// set the overall background color
		controlButton = new Button();
		controlButton.setTitle("Next Wave");
		// set button location to the interface 
		controlButton.setSize(120, 40);
		controlButton.setLocation(10, 10);
		bannerView.add(controlButton);
		
		// this label should be updated 
		String wave = "1/10";
		infoLabel = new Label();
		infoLabel.setText("Wave : "+wave);
		infoLabel.setSize(120, 40);
		infoLabel.setLocation(140, 10);
		bannerView.add(infoLabel);
		
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
		PlayingScene.this.add(bottomView);
		
		//Quit button
		backButton = new Button();
		backButton.setTitle("Quit");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 10);
		bottomView.add(backButton);
		
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
		inspectorView.setBackground(new Color(0xEEEEEE));
		this.add(inspectorView);
		
	}	

	@Override
	protected void initEvents() {
		super.initEvents();
		
		//perform the nextWave function to create the next wave of critters
		
		controlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		/**
		 * perform the exit function to go back to the main scene
		 * 
		 */
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				 play.deleteObserver(PlayingScene.this);
				 Play.destroy();
				 
				 PlayingScene.this.viewFlow.pop();
				
			}
		});
		
		mapView.setSelectionListener(this);
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
				command.setTitle("Build " + towerManager.createTower().getName());
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
		 * Method commands.
		 * @return List<Command>  
         *@see com.soen6441.ui.common.IInspectable#commands() 
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
		
		
		 * @return String  * @see com.soen6441.ui.common.IInspectable#subtitle()  */
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
				}	
			} else if (command == refundCommand) {
				play.earnCoins(tower.getSellPrice());
				play.getMap().removeItem(tower);
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
		
		
		 * @return String  * @see com.soen6441.ui.common.IInspectable#title()  */
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
		
		
		 * @return String  * @see com.soen6441.ui.common.IInspectable#description()  */
		@Override
		public String description() {
		
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
