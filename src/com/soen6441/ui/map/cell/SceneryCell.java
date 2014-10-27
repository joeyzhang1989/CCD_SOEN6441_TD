package com.soen6441.ui.map.cell;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.soen6441.ui.map.MapItemCell;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class SceneryCell extends MapItemCell {
	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		super.paintSelection(g);
	}
}
