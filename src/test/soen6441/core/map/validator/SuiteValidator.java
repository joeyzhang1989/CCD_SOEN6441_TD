package test.soen6441.core.map.validator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@SuiteClasses({ ConnectionValidatorTest.class,
		EndPointQuantityValidatorTest.class, 
		RoadQuantityValidatorTest.class,
		StartPointQuantityValidatorTest.class })


public class SuiteValidator {

}
