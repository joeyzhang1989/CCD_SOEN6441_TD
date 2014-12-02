package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.soen6441.core.play.Play;
import com.soen6441.core.play.PlayManager;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;

/**
 * 
 * This class defines the mainscene. On the Main interface of our game, there
 * are 3 buttons presented: Playbutton, Editbutton and Newmapbutton
 * 
 * @author Mengyao Wang
 * @author Chenglong Zhang
 * @see
 * @version $Revision: 1.0 $
 */

public class MainScene extends View {
	
	/*
	 * Mark - View - Properties
	 */

	private Button loadGameButton;

	private Button newGameButton;
	
	private Button editButton;

	private Button newMapButton;


	/*
	 * Mark - View - Life Cycle
	 */

	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		this.setBackground(new Color(0x242424));
		
		// add a label in the mainscene;
		Label label = new Label();
		label.setText("X-TD");
		label.setFontSize(100);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setLocation(160, 130);
		label.setSize(500, 100);
		this.add(label);

		// add a playbutton in the mainscene
		loadGameButton = new Button();
		loadGameButton.setTitle("Load Game");
		loadGameButton.setLocation(300, 300);
		loadGameButton.setSize(200, 40);
		this.add(loadGameButton);
		

		// add a newGameButton in the mainscene
		newGameButton = new Button();
		newGameButton.setTitle("New Game");
		newGameButton.setLocation(300, 350);
		newGameButton.setSize(200, 40);
		this.add(newGameButton);

		// add an editbutton in the mainscene
		editButton = new Button();
		editButton.setTitle("Edit a map");
		editButton.setLocation(300, 400);
		editButton.setSize(200, 40);
		this.add(editButton);

		// add a newmapbutton in the mainscene
		newMapButton = new Button();
		newMapButton.setTitle("Create new map");
		newMapButton.setLocation(300, 450);
		newMapButton.setSize(200, 40);
		this.add(newMapButton);

	}
	
	
	@Override
	protected void initEvents() {
		loadGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = openGameFile();
				
				if ( file != null ) {
					PlayManager playManager = new PlayManager();
					Play play = playManager.read(file);
					Play.setPlay(play);

					RankingScene scene = new RankingScene();
					MainScene.this.viewFlow.push(scene);
				}
			}
		});
		
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = openMapFile();
				
				if ( file != null ) {
					PlayManager playManager = new PlayManager();
					Play play = playManager.read(file);
					Play.setPlay(play);

					NewGameScene newGameScene = new NewGameScene();
					MainScene.this.viewFlow.push(newGameScene);
				}
			}
		});

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				File file = openMapFile();
				
				if ( file != null ) {
					PlayManager playManager = new PlayManager();
					Play play = playManager.read(file);
					Play.setPlay(play);
					
					EditingScene editingScene = new EditingScene();
					MainScene.this.viewFlow.push(editingScene);
				}
			}
		});

		newMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewMapScene newMapScene = new NewMapScene();
				MainScene.this.viewFlow.push(newMapScene);
			}
		});
	}


	/**
	 * Method openFile.
	 * @return File
     */
	private File openMapFile(){
		JFileChooser fileChooser = new JFileChooser(new File("maps/"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fileChooser.setFileFilter(filter);
	
		int option = fileChooser.showOpenDialog(MainScene.this);

		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();	
			return file;
		} else {
			return null;
		}
	}
	
	/**
	 * Method openGameFile.
	 * @return File
	 */
	private File openGameFile(){
		JFileChooser fileChooser = new JFileChooser(new File("games/"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fileChooser.setFileFilter(filter);
	
		int option = fileChooser.showOpenDialog(MainScene.this);

		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();	
			return file;
		} else {
			return null;
		}
	}
	
}
