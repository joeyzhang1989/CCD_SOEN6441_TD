package com.soen6441.ui.common;

import java.util.List;

//not finish
/**
 * This is the interface inspectable. 
 * Every object that implements inspectable will have options 
 * displayed on the right side, as explained in Inspector view.
 * @author Chenglong Zhang
 * @author JeanRaymondDaher
 * @see InspectorView
 * @version $Revision: 1.0 $
 */
public interface IInspectable{

	/**
	 * Method title.
	 * @return String
	 */
	public String title();
	/**
	 * Method subtitle.
	 * @return String
	 */
	public String subtitle();
	//public Image image;
	/**
	 * Method description.
	 * @return String
	 */
	public String description();
	/**
	 * Method commands.
	 * @return List<Command>
	 */
	public List<Command> commands();
	
	/**
	 * Method execute.
	 * @param command Command
	 */
	public void execute (Command command);

}
