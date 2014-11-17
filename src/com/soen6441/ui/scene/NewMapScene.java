package com.soen6441.ui.scene;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.play.Play;
import com.soen6441.ui.parallel.*;

/**
 * This class is used to create the weight and height of a new map, and with two
 * buttons that the abilities are go to next page or back to last page.
 * 
 * @author Mengyao Wang
 * 
 * @author Chenglong Zhang
 * 
 * @version $Revision: 1.0 $
 */

public class NewMapScene extends View {

	/*
	 * Mark - View - Properties
	 */
	
	private TextField widthTextField;
	private TextField heightTextField;
	private TextField nameTextField;
	private Label widthTextLabel;
	private Label heightTextLabel;
	private Label nameTextLabel;
	private Button nextButton;
	private Button backButton;
	
	/*
	 * Mark - View - Life Cycle
	 */
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		// add a label of widthText
		widthTextLabel = new Label();
		widthTextLabel.setText("Width");
		widthTextLabel.setSize(200, 40);
		widthTextLabel.setLocation(220, 150);
		this.add(widthTextLabel);

		this.widthTextField = new TextField();
		this.widthTextField.setText("");
		this.widthTextField.setSize(200, 40);
		this.widthTextField.setLocation(300, 150);
		this.add(widthTextField);

		heightTextLabel = new Label();
		heightTextLabel.setText("Height");
		heightTextLabel.setSize(200, 40);
		heightTextLabel.setLocation(220, (int)widthTextLabel.getLocation().getY() + 50);
		this.add(heightTextLabel);

		this.heightTextField = new TextField();
		this.heightTextField.setText("");
		this.heightTextField.setSize(200, 40);
		this.heightTextField.setLocation(300, 200);
		this.add(heightTextField);
		
		nameTextLabel = new Label();
		nameTextLabel.setText("Name");
		nameTextLabel.setSize(200, 40);
		nameTextLabel.setLocation(220, (int)heightTextLabel.getLocation().getY() + 50);
		this.add(nameTextLabel);
		
		this.nameTextField = new TextField();
		this.nameTextField.setText("");
		this.nameTextField.setSize(200, 40);
		this.nameTextField.setLocation(300, (int)heightTextField.getLocation().getY() + 50);
		this.add(nameTextField);

		nextButton = new Button();
		nextButton.setTitle("Next");
		nextButton.setLocation(300, (int)nameTextField.getLocation().getY() + 50);
		nextButton.setSize(200, 40);
		this.add(nextButton);

		backButton = new Button();
		backButton.setTitle("BACK");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 540);
		this.add(backButton);

	}

	@Override
	protected void initEvents() {
		nextButton.addActionListener(new ActionListener() {

			@Override
			/*
			 * perform the function that click the nextbutton to go to mainscene
			 */
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});

		backButton.addActionListener(new ActionListener() {

			@Override
			/*
			 * perform the function that click the backbutton to go to
			 * editingscene
			 */
			public void actionPerformed(ActionEvent e) {
				NewMapScene.this.viewFlow.pop();
			}
		});

	}
	

	/*
	 * Mark - Creation - Methods
	 */
	
	private void next() {
		if(!validateInput()){
			return;
		}
		
		if(!validateFileName()){
			return;
		}
		
		String widthString = widthTextField.getText();
		String heightString = heightTextField.getText();
		
		int width = Integer.valueOf(widthString);
		int height = Integer.valueOf(heightString);
		
		Play play = Play.currentPlay();
		GridMap gridMap = new GridMap();
		gridMap.setWidth(width);
		gridMap.setHeight(height);
		play.setMap(gridMap);
		
		String nameString = nameTextField.getText();
		File file = new File("maps/" + nameString + ".tdm.xml");
		
		EditingScene editingScene = new EditingScene();
		editingScene.setWorkingFile(file);
		
		this.viewFlow.push(editingScene);
	
	}
	
	/*
	 * Mark - creation - method
	 */
	/**
	 * Method validateInput.
	 * @return boolean
     */
	private boolean validateInput() {
		int width;
		int height;
		String widthString, heightString;
		widthString = widthTextField.getText();
		
		try {
			width = Integer.valueOf(widthString);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The range of the width is 4-15.");
			return false;
		}

		if (width < 4 || width > 15) {
			JOptionPane.showMessageDialog(this, "The range of the width is 4-15.");
			return false;
		}
		
		heightString = heightTextField.getText();
		
		try {
			height = Integer.valueOf(heightString);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The range of the height is 4-12.");
			return false;
		}

		if (height < 4 || height > 12) {
			JOptionPane.showMessageDialog(this, "The range of the height is 4-12.");
			return false;
		}
		return true;
	}
	
	/**
	 * Method validateFileName.
	 * @return boolean
     */
	private boolean validateFileName() {
		String nameString = nameTextField.getText();
		File file = new File("maps/" + nameString +".tdm.xml");
		if(file.exists()){
			JOptionPane.showMessageDialog(this, "This name of file is exist.");
			return false;
		}
		return true;
	}
}
