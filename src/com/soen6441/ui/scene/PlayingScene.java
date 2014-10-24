package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.Road;
import com.soen6441.core.play.Play;
import com.soen6441.core.tower.Tower;
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
 */
public class PlayingScene extends View implements Observer, GridViewSelectionListener{
	
	private Play play;
	
	/**
	 * 
	 * these properties are defined in the ui.parallel package that inherited from javax.swing
	 */
	//bannerView properties
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
		int x;
		int y;
		/* 
		 * get the map information from the mapView and set the 
		 * map to the center of GridmapView
		 */
		mapView = new MapView();
		mapView.setMap(play.getMap());
	    mapView.setSize(this.mapView.suggestedSize());
		x=(600-this.mapView.suggestedSize().height)/2;
		y=(800-180-this.mapView.suggestedSize().width)/2;
		mapView.setLocation(x, y);
		add(mapView);
		
		//inspectorView
		inspectorView  = new InspectorView();
		inspectorView.setLocation(620, 60);
		inspectorView.setSize(180,480);
		inspectorView.setBackground(new Color(0xEEEEEE));
		this.add(inspectorView);
		
		// send the notifications to the inspectorView
		inspectorView.setOn(new InspectableScenary());
		inspectorView.update();
		
	}	
	

	@Override
	protected void initEvents() {
		super.initEvents();
		
		//perform the nextWave function to create the next wave of critters
		
		controlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");
			}
		});
		
		/**
		 * perform the exit function to go back to the main scene
		 * 
		 */
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 PlayingScene.this.viewFlow.pop();
				 play.deleteObserver(PlayingScene.this);
				
			}
		});
		
		mapView.setSelectionListener(this);
	}
	
	/*
	 * Observer
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
	


	@Override
	public void gridViewDidSelect() {
		System.out.println("...");
		MapItem mi = play.getMap().selectedItem();
		//MapItemCellFactory.cellFromItem(mi);
		if (mi == null){
			inspectorView.setOn(new SelectScenery());
			inspectorView.update();
		} else if (mi instanceof Road){
			Road road = (Road)mi;
			inspectorView.setOn(new SelectRoad(road));
			inspectorView.update();
		}
		else if (mi instanceof Tower){
			inspectorView.setOn(new SelectTower());
			inspectorView.update();
		}
	  }
		
	
	/*
	 * Inner Classes
	 */
	
	/**
	 * inner class that implements the IInspectable interface to 
	 * add the inspectorView into the PlayingScene .
	 * update the inspector view due to the operations 
	 * 
	 * @author Chenglong Zhang 
	 * @version $Revision: 1.0 $
	 */
	
	private class InspectableScenary implements IInspectable
	{
		private Command buildBottleTower;
		private Command buildMudTower;
		private Command tempToTestObserver;
		
		public InspectableScenary() {
			buildBottleTower = new Command("Build Bottle Tower", "100$");
			buildMudTower = new Command("Build Mud Tower", "100$");
			tempToTestObserver = new Command("Observer Test", "");
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
			return "An Empty Space";
		}

		/**
		 * Method description.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#description()
		 */
		@Override
		public String description() {
			return "You can build anything on it";
		}

		/**
		 * Method commands.
		 * @return List<Command>
		 * @see com.soen6441.ui.common.IInspectable#commands()
		 */
		@Override
		public List<Command> commands() {
			List<Command> commands = new ArrayList<Command>();
			commands.add(buildBottleTower);
			commands.add(buildMudTower);
			commands.add(tempToTestObserver);
			return commands;
		}

		/**
		 * Method execute.
		 * @param command Command
		 * @see com.soen6441.ui.common.IInspectable#execute(Command)
		 */
		@Override
		public void execute(Command command) {
			if(command == buildBottleTower){
				System.out.println("Going to build a bottle tower");
			} else if(command == buildMudTower){
				System.out.println("Going to build a mud tower");
			} else if(command == tempToTestObserver){
				play.earnCoins(25);
				play.setLife(1000);
			}
			
		}		
	}	
	
	
	/**
	 * 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting tower scene the inspector view should update 
	 * 
	 * @author Chenglong Zhang 
	 * @version $Revision: 1.0 $
	 */
	
	private class SelectTower implements IInspectable
	{
		// TowerManagerFactory tmf = TowerManagerFactory.currentManagerFactory();
		// TowerManager tm = tmf.getManager("BottleTower");
		//	Tower tower = tm.createTower();
		/**
		 * Method title.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		private Command Upgrade;
		private Command Refund;

		
		public SelectTower() {
			Upgrade = new Command("Upgrade Tower", "50$");
			Refund = new Command("Refund Tower", "100$");
		}
		@Override
		public String title() {
			
			return "Tower";
		}

		/**
		 * Method subtitle.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#subtitle()
		 */
		@Override
		public String subtitle() {
			
			return null;
		}

		/**
		 * Method description.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#description()
		 */
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
			List<Command> commands = new ArrayList<Command>();
			commands.add(Upgrade);
			commands.add(Refund);
			return commands;
		}

		/**
		 * Method execute.
		 * @param command Command
		 * @see com.soen6441.ui.common.IInspectable#execute(Command)
		 */
		@Override
		public void execute(Command command) {
			if(command == Upgrade){
				System.out.println("Upgrade");
			} else if(command == Refund){
				System.out.println("Refund");
			} 
		}		
	}	



	/**
	 *
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a road scene that the inspector view should update 
	 * 
	 * @author Chenglong Zhang 
	 * @version $Revision: 1.0 $
	 */
	private class SelectRoad implements IInspectable
	{
		private Road road;
		
		public SelectRoad(Road road){
			this.road = road;
		}
		/**
		 * Method title. check the selected road type to update the inspectorView Title
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		@Override
		public String title() {
			Road.Type type = road.getType();
	
			if (type == Road.Type.NORMAL){
				return "NORMALROAD";
			}else if (type ==Road.Type.START){
				return "STARTPOINT";
			}else if (type == Road.Type.END){
				return "ENDPOINT";
			}
			return null;
		}
		

		
		
		/**
		 * Method subtitle.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#subtitle()
		 */
		@Override
		public String subtitle() {
		
			return null;
		}

		/**
		 * Method description.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#description()
		 */
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


	/**
	 *
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a empty space scene that the inspector view should update 
	 * 
	 * @author Chenglong Zhang 
	 * @version $Revision: 1.0 $
	 */
	private class SelectScenery implements IInspectable
	{

		public SelectScenery() {
			// TODO Auto-generated constructor stub
		}

		/**
		 * Method title.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		@Override
		public String title() {
		
			return "Selected";
		}

		/**
		 * Method subtitle.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#subtitle()
		 */
		@Override
		public String subtitle() {
		
			return null;
		}

		/**
		 * Method description.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#description()
		 */
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
