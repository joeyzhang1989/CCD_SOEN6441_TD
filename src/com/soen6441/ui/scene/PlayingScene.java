package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.soen6441.core.Play;
import com.soen6441.ui.common.Command;
import com.soen6441.ui.common.IInspectable;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextField;
import com.soen6441.ui.parallel.View;
import com.soen6441.ui.parallel.ViewFlow;
import com.soen6441.ui.parallel.Window;

import demo.soen6441.ui.viewFlow.ViewFlowDemoScene1;
import demo.soen6441.ui.viewFlow.ViewFlowDemoScene2;




/**
 * @author chenglong zhang 
 * 
 * this is PlayingScene class is responsible of the playscene view and showing the 
 * corresponding information of the related models
 * not finish with the inner classes
 */
public class PlayingScene extends View{
	
	/**
	 * 
	 * these properties are defined in the ui.parallel package that inherited from javax.swing
	 */
	private Label moneyLabel;
	
	private Label infoLabel;
	
	private Label lifelabel;
	
	private Label money;
	
	private Label life;
	
	private Button controlButton;
	
	private Button backButton;
	
	private Button saveButton;
	
	private MapView mapView;
	
	private TextField grid,
					  inspector;
					  
	
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
		
		//moneylabel
		moneyLabel = new Label();
		moneyLabel.setText("Money");
		moneyLabel.setSize(120, 40);
		moneyLabel.setLocation(400, 10);
		bannerView.add(moneyLabel);   
		
		//label to show the coins change
		money = new Label();
		money.setText("1000");
		money.setSize(80,40);
		money.setLocation(520,10);
		bannerView.add(money);
		Play pl = Play.currentPlay();
		//THIS SHOULD BE SET TO PLAY.GETCOINS()
		
		//lifelabel
		lifelabel = new Label();
		lifelabel.setText("Life");
		lifelabel.setSize(120, 40);
		lifelabel.setLocation(640, 10);
		bannerView.add(lifelabel);   
		
		//lable to store the life change
		life = new Label();
		life.setText("1000");
		//THIS SHOULD BE SET TO PLAY.GETLIFE()
		life.setSize(80,40);
		life.setLocation(720,10);
		bannerView.add(life);
		
		//create the bottomView to the PlayingScene
		View bottomView = new View();
		bottomView.setLocation(0, 540);
		bottomView.setSize(800, 40);
		PlayingScene.this.add(bottomView);
		
		//Save button
		saveButton = new Button();
		saveButton.setTitle("SAVE");
		saveButton.setSize(60, 20);
		saveButton.setLocation(730, 10);
		bottomView.add(saveButton);
		
		//Quit button
		backButton = new Button();
		backButton.setTitle("Quit");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 10);
		bottomView.add(backButton);
		
		// buttons on the inspector view
		
		//grid 
		grid = new TextField();
		grid.setLocation(10, 60);
		grid.setSize(600,480);
		grid.setBackground(Color.BLACK);
		this.add(grid);
		
		//inspector
		inspector  = new TextField();
		inspector.setLocation(620, 60);
		inspector.setSize(180,480);
		inspector.setBackground(Color.BLACK);
		this.add(inspector);
		
		//get the size of the map then put into the interface, leave for the map instance
		
			//double height=this.map.getMap().getHeight();
			//double width=this.map.getMap().getWidth();
		/*	height=15;
			width=15;
			View view = new View();
			view.setLocation(10, 50);
			view.setSize(485,485);
			for(int i=0;i<height;i++)
					for(int j=0;j<width;j++){
						
					}
			*/
			//view.setBackground(Color.BLACK);
			//this.add(view);
	}
<<<<<<< HEAD
//	public static void main(String[] args) {
//		//PlayingScene ps = new PlayingScene();
//		//new Window(ps);
//		ViewFlow viewFlow = new ViewFlow();
//		viewFlow.push(new PlayingScene());
//		new Window(viewFlow);
//	}
	
	

=======
	
>>>>>>> 004cfd049d4837eceb8534676b9ff0e0da157f98
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
				 MainScene ms= new  MainScene ();
				//wait the MainScene to finish implementation
				 PlayingScene.this.viewFlow.push(ms);
				
			}
		});
		
		/**
		 * perform the save function to save the game for next load
		 * 
		 */
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");
			}
		});
		
		
	}
	
	//
	
	/**
	 * @author chenglong zhang 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting tower scene the inspector view should update 
	 */
	private class SelectTower implements IInspectable
	{

		@Override
		public String title() {
			
			return null;
		}

		@Override
		public String subtitle() {
			
			return null;
		}

		@Override
		public String description() {
			
			return null;
		}

		@Override
		public List<Command> commands() {
			
			return null;
		}

		@Override
		public void execute(Command command) {
			
			
		}		
	}	



	/**
	 * @author chenglong zhang 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a road scene that the inspector view should update 
	 */
	private class SelectRoad implements IInspectable
	{

		@Override
		public String title() {
		
			return null;
		}

		@Override
		public String subtitle() {
		
			return null;
		}

		@Override
		public String description() {
		
			return null;
		}

		@Override
		public List<Command> commands() {
		
			return null;
		}

		@Override
		public void execute(Command command) {
		
		
		}
	
	}


	/**
	 * @author chenglong zhang 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 * selecting a empty space scene that the inspector view should update 
	 */
	private class SelectEmptySpace implements IInspectable
	{

		@Override
		public String title() {
		
			return null;
		}

		@Override
		public String subtitle() {
		
			return null;
		}

		@Override
		public String description() {
		
			return null;
		}

		@Override
		public List<Command> commands() {
		
			return null;
		}

		@Override
		public void execute(Command command) {
		
		
		}
	}
}
