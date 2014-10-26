package test.soen6441.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.soen6441.ui.common.SuiteCommon;
import test.soen6441.ui.map.SuiteMap;

@RunWith(Suite.class)
@SuiteClasses({SuiteCommon.class, SuiteMap.class})
public class SuiteUI {

}
