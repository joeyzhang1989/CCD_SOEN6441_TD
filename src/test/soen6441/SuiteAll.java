package test.soen6441;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.soen6441.core.SuiteCore;
import test.soen6441.ui.SuiteUI;

/**
 *
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@SuiteClasses({SuiteCore.class, SuiteUI.class})
public class SuiteAll {

}
