package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

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
	
	private TextField fileNameTextField;
	private Label fileNameLabel;
	private TextField playerNameTextField;
	private Label playerNameLabel;
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
		
		
		fileNameLabel = new Label();
		fileNameLabel.setText("File Name");
		fileNameLabel.setSize(290, 40);
		fileNameLabel.setLocation(0, 200);
		fileNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(fileNameLabel);
		
		fileNameTextField = new TextField();
		fileNameTextField.setText("");
		fileNameTextField.setSize(200, 40);
		fileNameTextField.setLocation(300, 200);
		this.add(fileNameTextField);
		
		playerNameLabel = new Label();
		playerNameLabel.setText("Player Name");
		playerNameLabel.setSize(290, 40);
		playerNameLabel.setLocation(0, 250);
		playerNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(playerNameLabel);
		
		playerNameTextField = new TextField();
		playerNameTextField.setText("");
		playerNameTextField.setSize(200, 40);
		playerNameTextField.setLocation(300, 250);
		this.add(playerNameTextField);

		nextButton = new Button();
		nextButton.setTitle("Next");
		nextButton.setLocation(300, 300);
		nextButton.setSize(200, 40);
		this.add(nextButton);

		backButton = new Button();
		backButton.setTitle("BACK");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 550);
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
		
		
		String nameString = fileNameTextField.getText();
		File file = new File("games/" + nameString + ".tdg.xml");
		
		play.setMapFilePath(play.getSourceFile().getName());
		play.setSourceFile(file);
		play.getMap().getLogger().clearLogs();
		play.setPlayerName(playerNameTextField.getText());
		
		new PlayManager().save(play.getSourceFile(), play);
		
		Play.setPlay(play);
		
		RankingScene scene = new RankingScene();
		this.viewFlow.push(scene);
	
	}
	
	/*
	 * Mark - creation - method
	 */
	
	/**
	 * Method validateFileName.
	 * @return boolean
     */
	private boolean validateFileName() {
		String nameString = fileNameTextField.getText();
		File file = new File("games/" + nameString +".tdg.xml");
		if(file.exists()){
			JOptionPane.showMessageDialog(this, "This name of file is exist.");
			return false;
		}
		return true;
	}

}
