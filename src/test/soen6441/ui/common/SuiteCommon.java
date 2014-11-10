package test.soen6441.ui.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * This is the suite test class for GridViewCells which will run all the common ui tests.
 * 
 * @author JeanRaymondDaher
 * @version $Revision: 1.0 $
 */

@RunWith(Suite.class)
@SuiteClasses({ GridViewTestCellManagement.class, GridViewTestDisplay.class,
		GridViewTestSelection.class })
public class SuiteCommon {

}
