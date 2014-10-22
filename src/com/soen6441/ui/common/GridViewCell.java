package com.soen6441.ui.common;
import com.soen6441.ui.parallel.View;
import com.soen6441.ui.scene.MainScene;

/**
 * This class defines a unit of the GridView. 
 * 
 * @author JeanRaymondDaher
 * @since 0.1
 * @see GridView
 */

public class GridViewCell extends View {

	private GridView gridView;

	/**
	 * Method getGridView.
	 * @return GridView
	
	/*
	 * Getters & Setters
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
	
	
	
}
