package com.soen6441.ui.common;




import java.util.List;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.ImageView;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;

// not finish 
/**
 * This class defines the inspector view. On the right side of our game the menu that will show editing options or tower information is the inspector view.
 * An element needs to be inspectable to be displayed on the view.
 * @author chenglong zhang, jean raymond daher
 * @see IInspectable
 */
public class InspectorView extends View{

	private Label titleLabel;
	private Label subtitleLabel;
	private ImageView imageView;
	private Label description;
	private List<Button> commandButtons;
	
	
	private IInspectable on;
	private List<Command> commands;
	
	public IInspectable getOn() {
		return on;
	}
	public void setOn(IInspectable on) {
		this.on = on;
	}
	
	
}
