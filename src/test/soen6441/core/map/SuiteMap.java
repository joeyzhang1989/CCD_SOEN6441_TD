package test.soen6441.core.map;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GridMapTest.class, MapPathTest.class, MapPointTest.class,
		MapPointTestFunctional.class, PathManagerTest.class })
public class SuiteMap {

}
