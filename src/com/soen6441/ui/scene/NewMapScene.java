package com.soen6441.ui.scene;

import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.soen6441.ui.parallel.*;

/**
 * This class is used to create the weight and height of a new map, and with two
 * buttons that the abilities are go to next page or back to last page.
 * 
 * @author Mengyao Wang
 * @author Chenglong Zhang
 * @version $Revision: 1.0 $
 */

public class NewMapScene extends View {
	private TextField widthTextField;
	private TextField heightTextField;
	private TextField nameTextField;
	private Label widthTextLabel;
	private Label heightTextLabel;
	private Label nameTextLabel;
	private Button nextButton;
	private Button backButton;

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
		nameTextLabel.setText("Height");
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
		backButton.setSize(100, 20);
		backButton.setLocation(10, 520);
		this.add(backButton);

	}

	private void next() {
		int width;
		java.lang.String wi, hi;
		wi = widthTextField.getText();
		try {
			width = Integer.valueOf(wi);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"The range of the width is 4-15");
			return;
		}

		if (width < 4 || width > 15) {
			JOptionPane.showMessageDialog(this,
					"The range of the width is 4-15");
			return;

		}
		
		hi = heightTextField.getText();
		try {
			width = Integer.valueOf(hi);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"The range of the height is 4-12");
			return;
		}

		if (width < 4 || width > 12) {
			JOptionPane.showMessageDialog(this,
					"The range of the height is 4-12");
			return;

		}
//		EditingScene editingScene = new EditingScene();
//		NewMapScene.this.viewFlow.push(editingScene);

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
}
