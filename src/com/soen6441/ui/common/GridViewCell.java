package com.soen6441.ui.common;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.soen6441.ui.parallel.View;
import com.soen6441.ui.scene.MainScene;

/**
 * This class defines a unit of the GridView. 
 * 
 * @author JeanRaymondDaher
 * @author Zhe Zhao
 * @see GridView
 */

public class GridViewCell extends View {
	
	/*
	 * Mark - Properties
	 */

	private GridView gridView;
	private GridPoint point;
	private boolean selected;
	

	@Override
	protected void initEvents() {
		super.initEvents();
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				GridViewCell cell = GridViewCell.this;
				cell.gridView.selectCell(cell);
			}
		});
	}

	/*
	 * Mark - Getters & Setters
	 */
	
	/**
	 * Method getGridView.
	 * @return GridView
	 */
	public GridView getGridView() {
		return gridView;
	}

	/**
	 * Method setGridView.
	 * @param gridView GridView
	 */
	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}

	public GridPoint getPoint() {
		return point;
	}

	public void setPoint(GridPoint point) {
		this.point = point;
	}	

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
