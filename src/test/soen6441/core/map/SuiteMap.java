package test.soen6441.core.map;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 */
@RunWith(Suite.class)
@SuiteClasses({ GridMapTestItemManagement.class, GridMapTestSelection.class,
		MapPathTest.class, MapPointTest.class, MapPointTestFunctional.class,
		PathManagerTest.class, MapItemSelectorTest.class})
public class SuiteMap {

}
