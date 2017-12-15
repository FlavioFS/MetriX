package all;

import junit.framework.Test;
import junit.framework.TestSuite;
import tests.concern.HDoSTest;
import tests.coupling.HCoTest;
import tests.coupling.RCoTest;
import tests.coupling.SCoTest;
import tests.ratio.RoCLoCTest;
import tests.ratio.RoFLoCTest;
import tests.ratio.RoTLoCTest;
import tests.size.NoEHTest;
import tests.size.NoGHTest;
import tests.size.NoHTest;
import tests.size.NoRTest;
import tests.size.NoSTest;
import tests.usage.EHMUTest;

/**
 * Test suite that runs tests for all metrics. 
 * @author Flavio Freitas
 *
 */
public class AllMetricsTest {

	/**
	 * Creates the "all metrics suite" by the addition of each metric provided by Metrix.
	 * @return A suite that runs every metric provided by Metrix.
	 */
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
		suite.addTestSuite(EHMUTest.class);
		
		// Ratio
		suite.addTestSuite(RoCLoCTest.class);
		suite.addTestSuite(RoTLoCTest.class);
		suite.addTestSuite(RoFLoCTest.class);
		
		// Concern
		suite.addTestSuite(HDoSTest.class);
		
		return suite;
	}

}
