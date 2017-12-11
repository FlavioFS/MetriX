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
		suite.addTestSuite(HCoExtractorTest.class);
		suite.addTestSuite(RCoExtractorTest.class);
		return suite;
	}

}
