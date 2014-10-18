package com.soen6441.ui.scene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.soen6441.ui.parallel.*;

/**
 * This class is used to create the weight and height of a new map,
 * and with two buttons that the abilities are go to next page or back to last page. 
 * 
 * @author Mengyao Wang
 * @author Chenglong Zhang
 */

public class NewMapScene extends View{
	private TextField widthTextField;
	private TextField heightTextField;
	private Label widthTextLabel;
	private Label heightTextLabel;
	private Button nextButton;
	private Button backButton;
	
	
	@Override
	protected void initSubviews(){
		super.initSubviews();
		// add a label of widthText
		widthTextLabel = new Label();
		widthTextLabel.setText("Width");
		widthTextLabel.setSize(200,40);
		widthTextLabel.setLocation(270,100);
		this.add(widthTextLabel);
		
		this.widthTextField = new TextField();
		this.widthTextField.setText("");
		this.widthTextField.setSize(200,40);
		this.widthTextField.setLocation(350,100);
		this.add(widthTextField);
		
		heightTextLabel = new Label();
		heightTextLabel.setText("Height");
		heightTextLabel.setSize(200,40);
		heightTextLabel.setLocation(270,150);
		this.add(heightTextLabel);
		
		this.heightTextField = new TextField();
		this.heightTextField.setText("");
		this.heightTextField.setSize(200,40);
		this.heightTextField.setLocation(350,150);
		this.add(heightTextField);
		
		nextButton = new Button();
		nextButton.setTitle("Next");
		nextButton.setLocation(350, 200);
		nextButton.setSize(200,40);
		this.add(nextButton);
		
		backButton=new Button();
		backButton.setTitle("BACK");
		backButton.setSize(80, 20);
		backButton.setLocation(10, 550);
		this.add(backButton);
		
	}
	@Override
	protected void initEvents()	{
		nextButton.addActionListener(new ActionListener(){
			
			@Override
			/**
			 * *perform the function that click the nextbutton to go to mainscene
			 */
			public void actionPerformed(ActionEvent e){
				EditingScene editingScene = new EditingScene();
				NewMapScene.this.viewFlow.push(editingScene);
			}
		});
	
		backButton.addActionListener(new ActionListener(){
			
			@Override
			/**
			 * *perform the function that click the backbutton to go to editingscene
			 */
			public void actionPerformed(ActionEvent e){
				NewMapScene.this.viewFlow.pop();
			}
		});

	}
}
