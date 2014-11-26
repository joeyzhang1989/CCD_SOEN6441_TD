package com.soen6441.ui.parallel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class TextView extends JTextArea {
	public TextView() {
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setEditable(false);
		this.setFocusable(false);
		this.initFont();
	}

	private void initFont() {
		this.setFont(new Font("Verdana", 0, 14));
		this.setForeground(new Color(0x00CCCC));
		this.setBackground(new Color(0x111111));
		this.setSelectionColor(new Color(0x111111));
		this.setSelectedTextColor(new Color(0x00CCCC));
	}
	
	/**
	 * Method setFontSize.
	 * @param fontSize int
	 */
	public void setFontSize(int fontSize){
		this.setFont(new Font("Verdana", 0, fontSize));
	}
}
