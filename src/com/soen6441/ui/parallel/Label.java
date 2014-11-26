package com.soen6441.ui.parallel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * A Parallel Class, which represents a label.
 *
 * @author Zhe Zhao
 *
 * @version $Revision: 1.0 $
 */
public class Label extends JLabel {
	public Label() {
		this.initFont();
	}

	/**
	 * Constructor for Label.
	 * @param text String
	 */
	public Label(String text) {
		super(text);
		this.initFont();
	}
	
	private void initFont() {
		this.setFont(new Font("Verdana", 0, 14));
		this.setForeground(new Color(0x00CCCC));
	}
	
	/**
	 * Method setFontSize.
	 * @param fontSize int
	 */
	public void setFontSize(int fontSize){
		this.setFont(new Font("Verdana", 0, fontSize));
	}
}
