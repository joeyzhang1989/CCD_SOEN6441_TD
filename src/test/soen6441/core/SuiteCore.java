package test.soen6441.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.soen6441.core.map.SuiteMap;
import test.soen6441.core.map.validator.SuiteValidator;
import test.soen6441.core.play.SuitePlay;
import test.soen6441.core.tower.SuiteTower;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@SuiteClasses({
	SuiteValidator.class,
	SuiteMap.class,
	SuitePlay.class,
	SuiteTower.class})

public class SuiteCore {

}
