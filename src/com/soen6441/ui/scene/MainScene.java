package com.soen6441.ui.scene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;
import com.soen6441.ui.parallel.ViewFlow;
import com.soen6441.ui.parallel.Window;

/**
 * 
 * This class defines the mainscene. On the Main interface of our game, there
 * are 3 buttons presented: Playbutton, Editbutton and Newmapbutton
 * 
 * @author Mengyao Wang
 * @author Chenglong Zhang
 * @see
 */

public class MainScene extends View {

	private Button playButton;

	private Button editButton;

	private Button newMapButton;

	/*
	 * override the method initSubviews in the super class View
	 */

	@Override
	protected void initSubviews() {
		super.initSubviews();
		// add a label in the mainscene;
		Label label = new Label();
		// set the information of the label;
		label.setText("Tower Defense");

		// The setFont is the method that is used to set the style of label.

		label.setFont(new Font("Cooper Std Black", 1, 60));
		label.setForeground(Color.orange);
		// set the location of the label
		label.setLocation(160, 100);
		// set the size of the label
		label.setSize(500, 250);
		this.add(label);

		// add a playbutton in the mainscene
		playButton = new Button();
		playButton.setTitle("Play");
		playButton.setLocation(300, 320);
		playButton.setSize(200, 40);
		this.add(playButton);

		// add an editbutton in the mainscene
		editButton = new Button();
		editButton.setTitle("Edit a map");
		editButton.setLocation(300, 370);
		editButton.setSize(200, 40);
		this.add(editButton);

		// add a newmapbutton in the mainscene
		newMapButton = new Button();
		newMapButton.setTitle("Create new map");
		newMapButton.setLocation(300, 420);
		newMapButton.setSize(200, 40);
		this.add(newMapButton);

	}

	@Override
	protected void initEvents() {
		playButton.addActionListener(new ActionListener() {

			@Override
			/*
			 * perform the function that click the playbutton to go to
			 * playingscene
			 */
			public void actionPerformed(ActionEvent e) {
				/*
				 * Open a window to select maps.
				 */
				JFileChooser fileChooser = new JFileChooser();
				
				playButton.addActionListener(this);

				if (e.getSource() == playButton) {
					int returnMap = fileChooser.showOpenDialog(MainScene.this);

					if (returnMap == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();

						PlayingScene playingScene = new PlayingScene();
						MainScene.this.viewFlow.push(playingScene);
					}

				}
			}
		});

		editButton.addActionListener(new ActionListener() {

			@Override
			/*
			 * perform the function that click the editbutton to go to
			 * editingscene
			 */
			public void actionPerformed(ActionEvent e) {
				EditingScene editingScene = new EditingScene();
				MainScene.this.viewFlow.push(editingScene);
			}
		});

		newMapButton.addActionListener(new ActionListener() {

			@Override
			/*
			 * perform the function that click the newmapbutton to go to
			 * newmapscene
			 */
			public void actionPerformed(ActionEvent e) {
				NewMapScene newMapScene = new NewMapScene();
				MainScene.this.viewFlow.push(newMapScene);
			}
		});
	}

	private static void showGui() {
		// Create and set up the window.
		JFrame frame = new JFrame("FileChooser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new MainScene());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * Method main.
	 * 
	 * @param args String[] perform the main interface at beginning
	 */
	public static void main(String[] args) {
		ViewFlow viewFlow = new ViewFlow();
		viewFlow.push(new MainScene());
		new Window(viewFlow);
	}

	

}
