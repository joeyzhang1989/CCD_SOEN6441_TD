package test.soen6441.ui.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;
/**
 * This is the test class for GridViewCells Selection inserted into the GridView. Making
 * sure the selected cell is the one we are working on.
 * 
 * @author JeanRaymondDaher
 * @version $Revision: 1.0 $
 */

public class GridViewTestSelection {
	
	private GridView temp= new GridView();
	private GridViewCell cell= new GridViewCell();
	
	@Before
	public void setUp() {
		this.temp.setSelectedCell(this.cell);
	}
	
	@Test
	public void testGetSelectedCell() {
		assertEquals(this.temp.getSelectedCell(),this.cell);
	}

	@Test
	public void testSetSelectedCell() {
		GridViewCell cell2= new GridViewCell();
		this.temp.setSelectedCell(cell2);
		assertEquals(cell2, this.temp.getSelectedCell());
	}

}
