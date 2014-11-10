package test.soen6441.core.map;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



import com.soen6441.core.map.MapItemSelector;
import com.soen6441.core.map.MapItemSelector.*;
import com.soen6441.core.map.MapPoint;

/**
 *@author Chenglong Zhang 
 */
public class MapItemSelectorTest {
	private MapItemSelector mapItemSelector;
	@Before
	public void setUp(){
		mapItemSelector = new MapItemSelector();
		
	}
	

	@Test
	public void testfliterByCircularArea(MapPoint mapPoint, double radius) {
		
	}

	@Test
	public void testfliterByTypes(MapItemSelectorTypeOption[] types) {
		
	}
	@Test
	public void testfliterByAmount(int amount) {
		
	}
	
	@Test
	public void testsortByDirectlyClosestToPoint () {
	}
	
	@Test
	public void testsortByOnPathClosestToEndPoint () {
	}
	
	@Test
	public void testsortByRandom () {
	
	}

}
