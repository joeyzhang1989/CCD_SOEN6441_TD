package test.soen6441.core.play;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * PlaySuite runs all Junits test cases of package "Play" as a suite.
 * 
 * @author Mohammad Ali 
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@SuiteClasses({ PlayTestSingleton.class,
				PlayManagerTest.class,
				PlayTest.class})
public class SuitePlay {

}
