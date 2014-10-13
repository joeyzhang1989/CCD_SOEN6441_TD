package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.soen6441.ui.common.Command;
import com.soen6441.ui.common.IInspectable;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextField;

import com.soen6441.ui.parallel.View;
import com.soen6441.ui.parallel.Window;



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
	
	private Button controlButton;
	
	private Button backButton;
	
	private Button saveButton;
	
	private MapView mapView;
	
	private TextField grid,inspector;
	
/**
 * override the method initSubviews in the super class View
 */
	@Override
	protected void initSubviews() {
		super.initSubviews();
		// add the controlButton to the play Scene
		controlButton = new Button();
		controlButton.setTitle("Next Wave");
		// set button location to the interface 
		controlButton.setSize(120, 40);
		controlButton.setLocation(10, 10);
		//button.setTitle("validate");
		this.add(controlButton);
		
		// this label should be updated 
		String Wave = "1/10";
		this.infoLabel = new Label();
		this.infoLabel.setText("Wave : "+Wave);
		this.infoLabel.setSize(120, 40);
		this.infoLabel.setLocation(140, 10);
		this.add(infoLabel);
		
		moneyLabel = new Label();
		moneyLabel.setText("Money");
		moneyLabel.setSize(120, 40);
		moneyLabel.setLocation(600, 10);
		this.add(moneyLabel);    
		
		lifelabel = new Label();
		lifelabel.setText("Life");
		lifelabel.setSize(120, 40);
		lifelabel.setLocation(720, 10);
		this.add(lifelabel);   
		
		//Save button
		saveButton=new Button();
		saveButton.setTitle("SAVE");
		saveButton.setSize(60, 20);
		saveButton.setLocation(730, 550);
		    
		//Quit button
		backButton=new Button();
		backButton.setTitle("Quit");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 550);
		this.add(saveButton);
		this.add(backButton);
		
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
	public static void main(String[] args) {
		PlayingScene so = new PlayingScene();
		new Window(so);
	}
	
	

	@Override
	protected void initEvents() {
		super.initEvents();
		
		controlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");
			}
		});
		
	}
	
	//
	
	/**
	 * 
	 * inner class that implements the IInspectable interface to capture 
	 * the event of mouse to change the value of the label, buttons etc.
	 */
	private class SelectTower implements IInspectable
	{

		@Override
		public String title() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String subtitle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String description() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Command> commands() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void execute(Command command) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	
}

