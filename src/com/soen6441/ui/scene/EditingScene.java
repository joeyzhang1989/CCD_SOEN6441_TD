package com.soen6441.ui.scene;


import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.*;

/**
 * This class is the Editing scene class where the user edits a created or new map.
 * 
 * @author JeanRaymondDaher
 * @since 0.1
 *
 */

public class EditingScene extends View {

	private Button controlButton;
	private Label infoLabel;
	private Label label;
	
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
	    this.label=new Label();
	    this.label.setText("1000");
	    //THIS SHOULD BE SET TO PLAY.GETCOINS()
	    this.label.setSize(150,40);
	    this.label.setLocation(700,10);
	    
		//back button
	    this.saveButton=new Button();
	    this.saveButton.setTitle("SAVE");
	    this.saveButton.setSize(60, 20);
	    this.saveButton.setLocation(730, 550);
	    //save button
	    this.backButton=new Button();
	    this.backButton.setTitle("BACK");
	    this.backButton.setSize(60, 20);
		this.backButton.setLocation(10, 550);
	    
	    
	    
		this.inspectorView=new InspectorView();		
		}
	
}
