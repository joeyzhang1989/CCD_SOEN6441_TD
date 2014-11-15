package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;




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

	private Button playButton;

	private Button editButton;

	private Button newMapButton;


	/*
	 * Mark - View - Life Cycle
	 */

	@Override
	protected void initSubviews() {
		super.initSubviews();
		// add a label in the mainscene;
		Label label = new Label();
		// set the information of the label;
		label.setText("X-TD");

		// The setFont is the method that is used to set the style of label.

		label.setFontSize(100);
		label.setForeground(Color.DARK_GRAY);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setLocation(160, 100);
		label.setSize(500, 250);
		this.add(label);

		// add a playbutton in the mainscene
		playButton = new Button();
		playButton.setTitle("Play");
		playButton.setFont(playButton.getFont().deriveFont(Font.BOLD));
		playButton.setLocation(300, 320);
		playButton.setSize(200, 40);
		this.add(playButton);

		// add an editbutton in the mainscene
		editButton = new Button();
		editButton.setTitle("Edit a Map");
		editButton.setFont(editButton.getFont().deriveFont(Font.BOLD));
		editButton.setLocation(300, 370);
		editButton.setSize(200, 40);
		this.add(editButton);

		// add a newmapbutton in the mainscene
		newMapButton = new Button();
		newMapButton.setTitle("Create New Map");
		newMapButton.setFont(newMapButton.getFont().deriveFont(Font.BOLD));
		newMapButton.setLocation(300, 420);
		newMapButton.setSize(200, 40);
		this.add(newMapButton);

	}
	
	
	@Override
	protected void initEvents() {
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = openFile();
				
				if ( file != null ) {
					PlayManager playManager = new PlayManager();
					playManager.read(file);

					PlayingScene playingScene = new PlayingScene();
					MainScene.this.viewFlow.push(playingScene);
				}
			}
		});

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				File file = openFile();
				
				if ( file != null ) {
					PlayManager playManager = new PlayManager();
					playManager.read(file);
					
					EditingScene editingScene = new EditingScene();
					editingScene.setWorkingFile(file);
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
	private File openFile(){
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
	
}
