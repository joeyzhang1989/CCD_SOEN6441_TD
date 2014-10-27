package test.soen6441.ui.common;
/**
 * This is the test class for GridViewCells display. Making
 * sure suggested size for GridView is computed and unit height, width setters.
 * 
 * @author JeanRaymondDaher
 * @version $Revision: 1.0 $
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.ui.common.GridView;

public class GridViewTestDisplay {

	private GridView temp = new GridView(5, 5);
	

	@Before
	public void setUp() {
		this.temp.setUnitHeight(5);
		this.temp.setUnitWidth(5);
	}
	
	@Test
	public void testSuggestedSize() {
		assertEquals(temp.suggestedSize().height, 5*5);
		assertEquals(temp.suggestedSize().width, 5*5);
	}

	// to test if set unit width to lets say 10, then if i add a cell , will the
	// location be right.
	@Test
	public void testSetUnitWidth() 
	{
		assertEquals(5,this.temp.getUnitWidth());
	}

	@Test
	public void testSetUnitHeight() {
		assertEquals(5,this.temp.getUnitHeight());
	}

}
