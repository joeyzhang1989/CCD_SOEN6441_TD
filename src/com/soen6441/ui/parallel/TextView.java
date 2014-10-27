package com.soen6441.ui.parallel;

import java.awt.Font;

import javax.swing.JTextArea;

/**
 */
public class TextView extends JTextArea {
	public TextView() {
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setEditable(false);
		this.initFont();
	}

	private void initFont() {
		this.setFont(new Font("Verdana", 0, 14));
	}
	
	/**
	 * Method setFontSize.
	 * @param fontSize int
	 */
	public void setFontSize(int fontSize){
		this.setFont(new Font("Verdana", 0, fontSize));
	}
}
