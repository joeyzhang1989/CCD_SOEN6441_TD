package com.soen6441.ui.parallel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextField;

/**
 * A Parallel Class, which represents a text field.
 *
 * @author Zhe Zhao
 *
 * @version $Revision: 1.0 $
 */
public class TextField extends JTextField{
	
	public TextField() {
		super();
		this.setBackground(new Color(0x242424));
		this.setForeground(new Color(0x00CCCC));
		this.setSelectionColor(new Color(0x00CCCC));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0x00CCCC));
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}
}
