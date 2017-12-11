package all;

import junit.framework.Test;
import junit.framework.TestSuite;
import tests.coupling.HCoTest;
import tests.coupling.RCoTest;
import tests.coupling.SCoTest;
import tests.size.NoEHTest;
import tests.size.NoGHTest;
import tests.size.NoHTest;
import tests.size.NoRTest;
import tests.size.NoSTest;

/**
 * Test suite that runs tests for all metrics. 
 * @author Flavio Freitas
 *
 */
public class AllMetricsTest {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllMetricsTest.class.getName());
		
		// Coupling
		suite.addTestSuite(RCoTest.class);
		suite.addTestSuite(SCoTest.class);
		suite.addTestSuite(HCoTest.class);
		
		// Size
		suite.addTestSuite(NoRTest.class);
		suite.addTestSuite(NoSTest.class);
		suite.addTestSuite(NoHTest.class);
		suite.addTestSuite(NoEHTest.class);
		suite.addTestSuite(NoGHTest.class);
		
		// Usage
		
		// Ratio
		
		// Concern
		
		return suite;
	}

}
