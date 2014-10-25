package com.soen6441.ui.common;

import java.awt.Dimension;

import com.soen6441.ui.parallel.View;

/**
 * This class defines the GridView, the main grid which is displayed on the view.
 *
 * @see GridViewCell
 * 
 * @author JeanRaymondDaher
 * 
 * @version $Revision: 1.0 $
 */

public class GridView extends View {

	/*
	 * Constructor
	 */
	
	public GridView(){
		this(10, 10);
	}
	
	/**
	 * Constructor for GridView.
	 * @param row int
	 * @param column int
	 */
	public GridView(int row, int column) {
		this.numberOfColumns = column;
		this.numberOfRows = row;
		this.cells = new GridViewCell[row][column];
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private int numberOfRows;
	private int numberOfColumns;

	private int unitWidth;
	private int unitHeight;

	private GridViewCell[][] cells;

	/*
	 * Mark - Basic - Methods
	 */
	
	/**
	 * This method will return the suggested width by taking the number of columns and multiplying it by the cell's width AND
	 * the suggested height by taking the number of rows and multiplying it by the cell's height
	
	 * @return Dimension 
	 * 			  		Object containing (width and height) */
	
	public Dimension suggestedSize() {
		int width=this.numberOfColumns*this.unitWidth;
		int height=this.numberOfRows*this.unitHeight;
		return new Dimension(width, height);
	}

	/*
	 * Getters & Setters for numberOfRows and numberOfColumns
	 */
	
	/**
	 * Method getNumberOfRows.
	 * @return int
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * Method setNumberOfRows.
	 * @param numberOfRows int
	 */
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
		this.cells=new GridViewCell[numberOfRows][this.numberOfColumns];
	}

	/**
	 * Method getNumberOfColumns.
	 * @return int
	 */
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * Method setNumberOfColumns.
	 * @param numberOfColumns int
	 */
	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
		this.cells=new GridViewCell[this.numberOfRows][numberOfColumns];
	}

	/*
	 * Getters & Setters for unitWidth and unitHeight
	 */

	/**
	 * Method getUnitWidth.
	 * @return int
	 */
	public int getUnitWidth() {
		return unitWidth;
	}

	/**
	 * Method setUnitWidth.
	 * @param unitWidth int
	 */
	public void setUnitWidth(int unitWidth) {
		this.unitWidth = unitWidth;
	}

	/**
	 * Method getUnitHeight.
	 * @return int
	 */
	public int getUnitHeight() {
		return unitHeight;
	}

	/**
	 * Method setUnitHeight.
	 * @param unitHeight int
	 */
	public void setUnitHeight(int unitHeight) {
		this.unitHeight = unitHeight;
	}

	/*
	 * Methods for managing GridViewCell: adding, removing and replacing a cell.
	 */

	/**
	 * Add a cell to the cells[row][column].
	 * 
	 * @param cell
	 *            Defines the cell that needs to be added
	 * @param point
	 *            Defines the location where the cell should reside
	 */
	public void addCell(GridViewCell cell, GridPoint point) {
		int row = point.getRow();
		int column = point.getColumn();
		if (this.cells[row][column] == null) {
			this.cells[row][column] = cell;
			cell.setPoint(point);
			cell.setGridView(this);
			
			cell.setLocation(column * unitWidth, row * unitHeight);
			this.add(cell);
		} else {
			System.out.println("cannot add - cell already present");
		}
	}

	/**
	 * Method removeCell.
	 * 
	 * @param cell
	 *            GridViewCell Loop through all the cells and remove the
	 *            appropriate cell.
	
	 */
	public void removeCell(GridViewCell cell) {
		int row = cell.getPoint().getRow();
		int column = cell.getPoint().getColumn();
		this.remove(cells[row][column]);
	}

	/**
	 * Loop through all the cells and replace the appropriate cell.
	 * 
	 * @param cell
	 *            The cell that needs to be replaced
	 * @param newCell
	 *            The new cell that needs to be added
	 */
	public void replaceCell(GridViewCell cell, GridViewCell newCell) {
	
		newCell.setSize(cell.getSize());
		newCell.setLocation(cell.getLocation());
		newCell.setPoint(cell.getPoint());
		newCell.setGridView(this);
		
		int row=cell.getPoint().getRow();
		int column=cell.getPoint().getColumn();
		this.remove(cells[row][column]);
		
		this.add(newCell);
		
		if (cell == this.selectedCell) {
			setSelectedCell(newCell);
		}
		
//		newCell.repaint();
//		this.repaint(newCell.getVisibleRect());
		this.validate();
		this.repaint();
	}

	/*
	 * Mark - Selection - Properties
	 */
	
	private GridViewCell selectedCell;
	private GridViewSelectionListener selectionListener;
	
	/*
	 * Mark - Selection - Getters & Setters
	 */

	/**
	 * Method getSelectedCell.
	 * @return GridViewCell
	 */
	public GridViewCell getSelectedCell() {
		return selectedCell;
	}

	/**
	 * Method setSelectedCell.
	 * @param selectedCell GridViewCell
	 */
	public void setSelectedCell(GridViewCell selectedCell) {

		if (this.selectedCell != selectedCell) {
			if (this.selectedCell != null) {
				this.selectedCell.setSelected(false);
			}
			this.selectedCell = selectedCell;
			if (this.selectionListener != null) {
				this.selectionListener.gridViewDidSelect();
			}
			if (this.selectedCell != null) {
				this.selectedCell.setSelected(true);
			}
		}
	}

	/**
	 * Method getSelectionListener.
	 * @return GridViewSelectionListener
	 */
	public GridViewSelectionListener getSelectionListener() {
		return selectionListener;
	}
	/**
	 * Method setSelectionListener.
	 * @param selectionListener GridViewSelectionListener
	 */
	public void setSelectionListener(GridViewSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}
	
}
