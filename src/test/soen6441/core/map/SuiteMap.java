package test.soen6441.core.map;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.soen6441.core.map.validator.SuiteValidator;

@RunWith(Suite.class)
@SuiteClasses({ GridMapTestItemManagement.class, GridMapTestSelection.class,
		MapItemSelectorTest.class, MapPathTest.class,
		MapPathTestFunctional.class, MapPointTest.class,
		MapPointTestFunctional.class, PathManagerTest.class})
public class SuiteMap {

}
