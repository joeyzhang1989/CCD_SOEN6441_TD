package com.soen6441.ui.common;

import com.soen6441.ui.parallel.View;

// not finish 
/**
 * This class defines the inspector view. On the right side of our game the menu that will show editing options or tower information is the inspector view.
 * An element needs to be inspectable to be displayed on the view.
 * @author chenglong zhang chenglongzhang931@gmail.com , jean raymond daher
 * @since version 1.0
 * @see Inspectable
 */
public class InspectorView extends View{

	private Label titleLabel;
	private Label subtitleLabel;
	private ImageView imageView;
	private Label description;
	private Button[] commandButtons;
	
	public Inspectable on;
	private Command[] commands;
}