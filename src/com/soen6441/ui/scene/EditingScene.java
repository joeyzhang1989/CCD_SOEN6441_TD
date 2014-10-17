package com.soen6441.ui.scene;


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
		
		//Validate button
		//ISSUE SHOWING NULL
		this.controlButton=new Button();
		this.controlButton.setLocation(10, 10);
		this.controlButton.setTitle("Validate");
		this.controlButton.setSize(120,40);
		this.add(controlButton);
		//InfoLabel : valid or not 
		String valid="Not valid";
		this.infoLabel=new Label();
		this.infoLabel.setText("Validation : "+valid);
		this.infoLabel.setSize(500, 40);
		this.infoLabel.setLocation(135, 10);
		this.add(infoLabel);
		//initial money Label
	    Label initialMoney=new Label();
	    initialMoney.setText("Initial Money");
	    initialMoney.setSize(300, 40);
	    initialMoney.setLocation(600, 10);
	    this.add(initialMoney);
	    //actual money
	    this.money=new TextField();
	    this.money.setText("1000");
	    //THIS SHOULD BE SET TO PLAY.GETCOINS()
	    this.money.setSize(80,40);
	    this.money.setLocation(700,10);
	    this.add(money);
		//back button
	    this.saveButton=new Button();
	    this.saveButton.setTitle("SAVE");
	    this.saveButton.setSize(60, 20);
	    this.saveButton.setLocation(730, 550);
	    this.add(saveButton);
	    //save button
	    this.backButton=new Button();
	    this.backButton.setTitle("BACK");
	    this.backButton.setSize(60, 20);
		this.backButton.setLocation(10, 550);
		this.add(this.backButton);
	    
	    
	    //this.mapView=new MapView();
		this.inspectorView=new InspectorView();	
		this.add(inspectorView);
	
		View view = new View();
		view.setLocation(70, 10);
		view.setSize(550, 550);
		this.add(view);
		
		
		
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

