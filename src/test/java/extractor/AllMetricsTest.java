package extractor;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for all metrics. 
 * @author Flavio Freitas
 *
 */
public class AllMetricsTest {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllMetricsTest.class.getName());
		suite.addTestSuite(RCoTest.class);
		suite.addTestSuite(SCoTest.class);
		suite.addTestSuite(HCoTest.class);
		return suite;
	}

}
