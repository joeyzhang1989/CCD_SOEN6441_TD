package test.soen6441.ui.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.ui.common.GridPoint;
import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;

/**
 * This is the test class for GridViewCells inserted into the GridView. Making
 * sure we are adding , removing and replacing correctly.
 * 
 * @author JeanRaymondDaher
 */

public class GridViewTestCellManagement {

	private GridView one;
	private GridViewCell cellA, cellC, cellD;
	private GridPoint gp1, gp2;

	@Before
	public void setUp() {
		one = new GridView(5, 5);
		cellA = new GridViewCell();
		cellC = new GridViewCell();
		cellD = new GridViewCell();
		gp1 = new GridPoint(0, 0);
		gp2 = new GridPoint(4, 4);
		this.one.addCell(cellA, gp1);
	}

	@Test
	public void testAddCell() {
		assertEquals(one.getCell(0, 0), cellA);
	}

	@Test
	public void testGetCell() {
		assertEquals(one.getCell(0, 0), cellA);
	}

	@Test
	public void testRemoveCell() {
		this.one.removeCell(cellA);
		assertEquals(null, one.getCell(0, 0));
	}

	@Test
	public void testReplaceCell() {
		this.one.addCell(cellD, gp2);
		this.one.replaceCell(cellD, cellC);
		assertEquals(one.getCell(4, 4), cellC);
	}

}
