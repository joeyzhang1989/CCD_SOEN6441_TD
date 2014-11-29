package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import com.soen6441.core.play.Play;
import com.soen6441.core.play.PlayManager;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextField;
import com.soen6441.ui.parallel.View;

/**
 * 
 * @author Mengyao Wang
 * 
 * @version $Revision: 1.0 $
 */

public class NewGameScene extends View {
	
	/*
	 * Mark - Context - Properties
	 */
	 
	private Play play;
	
	/*
	 * Mark - View - Properties
	 */
	
	private TextField nameTextField;
	private Label nameTextLabel;
	private Button nextButton;
	private Button backButton;
	
	/*
	 * Mark - View - Life Cycle
	 */
	protected void init() {
		super.init();
		this.play = Play.currentPlay();
	}
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		this.setBackground(new Color(0x242424));
		
		
		nameTextLabel = new Label();
		nameTextLabel.setText("Name");
		nameTextLabel.setSize(200, 40);
		nameTextLabel.setLocation(220, 200 + 50);
		this.add(nameTextLabel);
		
		this.nameTextField = new TextField();
		this.nameTextField.setText("");
		this.nameTextField.setSize(200, 40);
		this.nameTextField.setLocation(300, 200 + 50);
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
				NewGameScene.this.viewFlow.pop();
			}
		});

	}
	

	/*
	 * Mark - Creation - Methods
	 */
	
	private void next() {
		if(!validateFileName()){
			return;
		}
		
		
		String nameString = nameTextField.getText();
		File file = new File("games/" + nameString + ".tdg.xml");
		
		play.setMapFilePath(play.getSourceFile().getName());
		play.setSourceFile(file);
		play.getMap().getLogger().clearLogs();
		
		new PlayManager().save(play.getSourceFile(), play);
		
		Play.setPlay(play);
		
		PlayingScene playingScene = new PlayingScene();
		this.viewFlow.push(playingScene);
	
	}
	
	/*
	 * Mark - creation - method
	 */
	
	/**
	 * Method validateFileName.
	 * @return boolean
     */
	private boolean validateFileName() {
		String nameString = nameTextField.getText();
		File file = new File("games/" + nameString +".tdg.xml");
		if(file.exists()){
			JOptionPane.showMessageDialog(this, "This name of file is exist.");
			return false;
		}
		return true;
	}

}
