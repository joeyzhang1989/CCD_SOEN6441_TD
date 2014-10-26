package test.soen6441.ui.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.ui.common.GridPoint;
import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;

public class GridViewTestCellManagement {

	private GridView one;
	private GridViewCell cellA, cellB, cellC;
	private GridPoint gp1, gp2;

	@Before
	public void setUp() {
		one = new GridView(5, 5);
		cellA = new GridViewCell();
		cellB = new GridViewCell();
		cellC = new GridViewCell();
		gp1 = new GridPoint(0, 0);
		gp2 = new GridPoint(4, 4);
	}

	private void oldTest(){
		this.one.addCell(cellA, gp1);
		assertEquals(one.getCell(0, 0), cellA);
		this.one.removeCell(cellA);
		assertEquals(null, one.getCell(0, 0));
		
		this.one.addCell(cellB, gp2);
		this.one.replaceCell(cellB, cellC);
		assertEquals(one.getCell(4, 4),cellC);
		
		assertEquals(one.getNumberOfColumns(),5);
		assertEquals(one.getNumberOfRows(),5);
	}
	
	@Test
	public void testAddCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testReplaceCell() {
		fail("Not yet implemented");
	}

}
