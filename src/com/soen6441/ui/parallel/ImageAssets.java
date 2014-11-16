package com.soen6441.ui.parallel;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 */
public class ImageAssets{
	/**
	 * Method imageNamed.
	 * @param name String
	 * @return Image
	 */
	public static Image imageNamed(String name){
		return new ImageIcon("images/" + name).getImage();
	}
}
