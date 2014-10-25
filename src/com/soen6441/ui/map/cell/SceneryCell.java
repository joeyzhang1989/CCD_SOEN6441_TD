package com.soen6441.ui.map.cell;

import java.awt.Color;
import java.awt.Graphics;

import com.soen6441.ui.map.MapItemCell;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class SceneryCell extends MapItemCell {
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0xFFFFFF));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
	}
}
