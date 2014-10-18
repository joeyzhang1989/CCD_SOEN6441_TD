package com.soen6441.ui.scene;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.*;

/**
 * This class is the Editing scene class where the user edits a created or new map.
 * 
 * @author JeanRaymondDaher
 * @author Mengyao Wang
 * @since 0.1
 *
 */

public class EditingScene extends View{

	private Button controlButton;
	private Label infoLabel;
	//private Label label;
	private TextField money;
	
	private MapView mapView;
	private InspectorView inspectorView;
	
	private Button backButton;
	private Button saveButton;
	

	
	protected void initSubviews() {
		super.initSubviews();
		
		View upperView = new View();
		upperView.setLocation(0, 0);
		upperView.setSize(800, 60);
		upperView.setBackground(Color.gray);
		
		
		//Validate button
		this.controlButton=new Button();
		this.controlButton.setLocation(10, 10);
		this.controlButton.setTitle("Validate");
		this.controlButton.setSize(120,40);
		//InfoLabel : valid or not 
		String valid="Not valid";
		this.infoLabel=new Label();
		this.infoLabel.setText("Validation : "+valid);
		this.infoLabel.setSize(500, 40);
		this.infoLabel.setLocation(135, 10);
		//initial money Label
	    Label initialMoney=new Label();
	    initialMoney.setText("Initial Money");
	    initialMoney.setSize(300, 40);
	    initialMoney.setLocation(600, 10);
	    //actual money
	    this.money=new TextField();
	    this.money.setText("1000");
	    //THIS SHOULD BE SET TO PLAY.GETCOINS()
	    this.money.setSize(80,40);
	    this.money.setLocation(700,10);

	    upperView.add(this.controlButton);
	    upperView.add(this.infoLabel);
	    upperView.add(initialMoney);
	    upperView.add(this.money);
	    
		this.add(upperView);

	 
	    
	    View  lowerView= new View();
	    lowerView.setLocation(0,550);
	    lowerView.setSize(800, 40);;
	   // lowerView.setBackground(Color.blue);

	    
	  
		//back button
	    this.saveButton=new Button();
	    this.saveButton.setTitle("SAVE");
	    this.saveButton.setSize(60, 20);
	    this.saveButton.setLocation(730, 0);
	    //save button
	    this.backButton=new Button();
	    this.backButton.setTitle("BACK");
	    this.backButton.setSize(60, 20);
		this.backButton.setLocation(10, 0);
		
		lowerView.add(this.saveButton);
		lowerView.add(this.backButton);
		lowerView.setBackground(Color.cyan);
	    this.add(lowerView);
	    
		
	    //this.mapView=new MapView();
		this.inspectorView=new InspectorView();	
		inspectorView.setLocation(620, 60);
		inspectorView.setSize(180,480);
		inspectorView.setBackground(new Color(0xEEEEEE));
		this.add(inspectorView);
		
	
<<<<<<< Updated upstream
		View view = new View();
		view.setLocation(70, 10);
		view.setSize(550, 550);
//		this.add(view);
=======
	//	View view = new View();
		//view.setLocation(70, 10);
		//view.setSize(550, 550);
>>>>>>> Stashed changes
		
		
		
	}
	
	@Override
	protected void initEvents()	{
		
	
		backButton.addActionListener(new ActionListener(){
			
			@Override
			/**
			 * *perform the function that click the backbutton to go to editingscene
			 */
			public void actionPerformed(ActionEvent e){
				EditingScene.this.viewFlow.pop();
			}
		});
	}
	
}

