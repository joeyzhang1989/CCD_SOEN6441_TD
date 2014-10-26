package test.soen6441.core.map;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.soen6441.ui.common.GridPoint;
import com.soen6441.ui.common.GridView;
import com.soen6441.ui.common.GridViewCell;

public class GridViewTest {

	private GridView one;
	private GridViewCell cellA,cellB,cellC;
	private GridPoint gp1,gp2;
	
	@Before
	public void Before(){
		one= new GridView(5,5);
		cellA=new GridViewCell();
		cellB=new GridViewCell();
		cellC=new GridViewCell();
		gp1=new GridPoint(0, 0);
		gp2=new GridPoint(5,5);
	}
	
	@Test
	public void testAdd(){
		
		this.one.addCell(cellA, gp1);
		assertEquals(one.getCell(0, 0), cellA);
	}
}
