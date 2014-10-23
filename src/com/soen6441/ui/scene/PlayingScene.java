package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.soen6441.core.play.Play;
import com.soen6441.ui.common.Command;
import com.soen6441.ui.common.IInspectable;
import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextField;
import com.soen6441.ui.parallel.View;




/**
 * 
 * 
 * this is PlayingScene class is responsible of the playscene view and showing the 
 * corresponding information of the related models
 * not finish with the inner classes
 * 
 * @author chenglong zhang 
 */
public class PlayingScene extends View implements Observer{
	
	private Play play;
	
	/**
	 * 
	 * these properties are defined in the ui.parallel package that inherited from javax.swing
	 */
	private Label coinsLabel;
	
	private Label infoLabel;
	
	private Label lifelabel;
	
	private Label money;
	
	private Label life;
	
	private Button controlButton;
	
	private Button backButton;
	
	private Button saveButton;
	
	private MapView mapView;
	
	private InspectorView inspectorView;
	
	@Override
	protected void init() {
		play = Play.currentPlay();
		play.addObserver(this);
		super.init();
	}
					  
	
/**
 * override the method initSubviews in the super class View
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
		bannerView.add(coinsLabel);
		
		//THIS SHOULD BE SET TO PLAY.GETCOINS()
		
		//lifelabel
		life = new Label();
		life.setText("Life");
		life.setSize(120, 40);
		life.setLocation(640, 10);
		bannerView.add(life);   
		
		//lable to store the life change
		lifelabel = new Label();
		lifelabel.setSize(80,40);
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
		
		// buttons on the inspector view
		
		//grid 
		mapView = new MapView();
		mapView.setLocation(0, 60);
		mapView.setSize(620,480);
		mapView.setBackground(new Color(0xDDDDDD));
	    mapView.setMap(play.getMap());
		this.add(mapView);
		
		//inspector
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
		/**
		 * perform the nextWave function to create the next wave of critters
		 * 
		 */
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
	
	/*
	 * Inner Classes
	 */
	
	/**
	 * inner class that implements the IInspectable interface to 
	 * add the inspectorView into the PlayingScene .
	 * update the inspector view due to the operations 
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
	 * @author chenglong zhang 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting tower scene the inspector view should update 
	 * @version $Revision: 1.0 $
	 */
	
	private class SelectTower implements IInspectable
	{

		/**
		 * Method title.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		@Override
		public String title() {
			
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
	 * @author chenglong zhang 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a road scene that the inspector view should update 
	 * @version $Revision: 1.0 $
	 */
	private class SelectRoad implements IInspectable
	{

		/**
		 * Method title.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		@Override
		public String title() {
		
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
	 * @author chenglong zhang 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a empty space scene that the inspector view should update 
	 * @version $Revision: 1.0 $
	 */
	private class SelectEmptySpace implements IInspectable
	{

		/**
		 * Method title.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		@Override
		public String title() {
		
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

	
}
