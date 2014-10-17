package com.soen6441.ui.scene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.soen6441.ui.parallel.*;

import demo.soen6441.ui.viewFlow.ViewFlowDemoScene2;

/**
 * This class defines the mainscene. On the Main interface of our game, 
 * there are 3 buttons presented: Playbutton, Editbutton and Newmapbutton
 * 
 * @author Mengyao Wang
 * @author Chenglong Zhang
 *  
*/

public class MainScene extends View{
	
	private Button playButton;
	private Button editButton;
	private Button newMapButton;

@Override
protected void initSubviews() {
	super.initSubviews();
	
	Label label = new Label();
	label.setText("Scene 2");
	label.setLocation(200, 100);
	label.setSize(200, 40);
	this.add(label);
	
	playButton = new Button();
	playButton.setTitle("Go back to Scene1");
	playButton.setLocation(200, 200);
	playButton.setSize(200, 40);
	this.add(playButton);
	
}

@Override
protected void initEvents() {
	playButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MainScene.this.viewFlow.pop();
		}
	});
}
}




