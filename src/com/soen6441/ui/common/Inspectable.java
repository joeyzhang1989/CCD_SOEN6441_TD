package com.soen6441.ui.common;

//not finish
/**
 * This is the interface inspectable. Every object that implements inspectable will have options displayed on the right side, as explained in Inspector view.
 * @author chenglong zhang chenglongzhang931@gmail.com , jean raymond daher
 * @since version 1.0
 * @see InspectorView
 */
public interface Inspectable {

	public String title;
	public String subtitle;
	public Image image;
	public String description;
	public Command[] commands;
	
	public void execute (Command command);

}