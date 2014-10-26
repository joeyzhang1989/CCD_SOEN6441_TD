package test.soen6441.core.map;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.ui.common.GridPoint;
import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;

public class GridViewTest {

	private GridView one;
	private GridViewCell cellA, cellB, cellC;
	private GridPoint gp1, gp2;

	@Before
	public void Before() {
		one = new GridView(5, 5);
		cellA = new GridViewCell();
		cellB = new GridViewCell();
		cellC = new GridViewCell();
		gp1 = new GridPoint(0, 0);
		gp2 = new GridPoint(4, 4);
	}

	@Test
	public void testLogic() {

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
}
