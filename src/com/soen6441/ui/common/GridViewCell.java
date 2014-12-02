package com.soen6441.ui.common;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.soen6441.ui.parallel.View;

/**
 * This class defines a unit of the GridView, a cell on the GridView. 
 * 
 * @see GridView
 * 
 * @author JeanRaymondDaher
 * @author Zhe Zhao
 *
 * @version $Revision: 1.0 $
 */

public class GridViewCell extends View {
	
	/*
	 * Mark - Basic - Properties
	 */

	private GridView gridView;
	private GridPoint point;
	

	/*
	 * Mark - Basic - Getters & Setters
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

	/**
	 * Method getPoint.	
	 * @return GridPoint
     */
	public GridPoint getPoint() {
		return point;
	}
	
	/**
	 * Method setPoint.
	 * @param point GridPoint
	 */
	public void setPoint(GridPoint point) {
		this.point = point;
	}	

	/*
	 * Mark - Event Handle - Methods
	 */

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
				cell.gridView.setSelectedCell(cell);
			}
		});
	}
	
	/*
	 * Mark - Selection - Properties
	 */

	private boolean selected;
	

	/*
	 * Mark - Selection - Getters & Setters
	 */
	
	/**
	 * Method isSelected.	
	 * @return boolean
     */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Method setSelected.
	 * @param selected boolean
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
		this.repaint();
	}
	
	
	
}
