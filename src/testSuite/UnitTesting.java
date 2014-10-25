package testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.soen6441.core.map.CoreSuite;
import test.soen6441.core.play.PlaySuite;
import test.soen6441.core.tower.TowerTestSuite;

/**
 * Class UnitTesting contains all the unit tests for the entire systems.The test Suites of all the packages
 *  will be added to this class, in order to run all test for the wntire system as a batch.  
 * 
 * @author Mohammad Ali 
 */
@RunWith(Suite.class)
@SuiteClasses({ PlaySuite.class,
				TowerTestSuite.class,
				CoreSuite.class})
public class UnitTesting {

}
