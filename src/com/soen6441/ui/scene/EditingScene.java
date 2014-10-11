package com.soen6441.ui.scene;
import java.awt.Graphics;

import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.*;

/**
 * This class is the Editing scene class where the user edits a created or new map.
 * 
 * @author JeanRaymondDaher
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
	
	
	
	//need to draw on view main user interface 
	// draw buttons at correct location
	// draw the whole background ->
	//maybe make a main method to run and see output

	@Override
	protected void initSubviews() {
		// TODO Auto-generated method
		//
		super.initSubviews();
		this.controlButton=new Button();
		this.infoLabel=new Label();
		this.label=new Label();
		
		
		this.inspectorView=new InspectorView();		
		
		
		this.backButton=new Button();
		this.saveButton=new Button();
	}
	
}
