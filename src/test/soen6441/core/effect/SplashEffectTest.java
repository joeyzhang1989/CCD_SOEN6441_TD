
package test.soen6441.core.effect;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.soen6441.core.critter.Critter;
import com.soen6441.core.effect.AffectableValue;
import com.soen6441.core.effect.SplashEffect;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapPath;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.play.Play;

/**
 * This class tests two main method of SplashEffect class.
 * @author Haiyang Sun
 *
 */
public class SplashEffectTest {
	
	private static SplashEffect effect = new SplashEffect();
	
	private static MapPath path;
	private static GridMap map;

	private static MapPoint p1;
	private static MapPoint p2;
	private static MapPoint p3;
	private static MapPoint p4;
	private static MapPoint p5;
	private static MapPoint p6;
	
	private static Critter c1;
	private static Critter c2;
	private static Critter c3;

	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Play play = new Play();
		map = new GridMap();
		map.setWidth(8);
		map.setHeight(8);
		play.setMap(map);
		map.setPlay(play);

		p1 = new MapPoint(0, 0);
		p2 = new MapPoint(0, 2);
		p3 = new MapPoint(2, 2);
		p4 = new MapPoint(0, 1);
		p5 = new MapPoint(1, 2);
		p6 = new MapPoint(3, 3);

		List<MapPoint> starts = new ArrayList<MapPoint>();
		starts.add(p1);
		map.setStartPoints(starts);

		List<MapPoint> ends = new ArrayList<MapPoint>();
		ends.add(p3);
		map.setEndPoints(ends);

		path = new MapPath();
		path.addLocation(p1);
		path.addLocation(p2);
		path.addLocation(p3);

		List<MapPath> paths = new ArrayList<MapPath>();
		paths.add(path);
		map.setPaths(paths);
		map.getPathManager().generateRoadItemsFromPaths();
		
		AffectableValue speed = new AffectableValue(10);
		c1 = new Critter();
		c1.setHp(100);
		c1.setSpeed(speed);
		map.addCritter(c1, p4);

		c2 = new Critter();
		c2.setHp(200);
		c2.setSpeed(speed);
		map.addCritter(c2, p5);
		
		c3 = new Critter();
		c3.setHp(300);
		c3.setSpeed(speed);
		map.addCritter(c3, p6);
		
		effect.setSplashDamage(10);
		effect.setSplashRange(100);
		effect.setOn(c3);
		
		
	}

	/**
	 * Test method for {@link com.soen6441.core.effect.SplashEffect#start()}.
	 */
	@Test
	public void testStart() {
		
		c3.addEffect(effect);
		assertEquals(90, c1.getHp());
		assertEquals(190, c2.getHp());
		
		
	}

}
