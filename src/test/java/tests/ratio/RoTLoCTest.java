package tests.ratio;

import java.io.File;

import junit.framework.Assert;
import metrix.ratio.RoTLoCExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

public class RoTLoCTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/RoTLoC/";
		
		testFilePath = "RoTLoCTest.java";
		testFileContent =
			"public class RoTLoCTest {				\n" +	// 1
			"										\n" +
			"	public void triggerRoTLoC () {		\n" +
			"		try {							\n" +		// 4
			"		}								\n" +
			"		catch (IOException e) {}		\n" +		// 6 (ends at catch clause)
			"	}									\n" +
			"										\n" +
			"}										\n";	// 9
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	public void testRoTLoC() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new RoTLoCExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals( (6.0-4.0)/(9-1), MeasureDataset.getMeasure("RoTLoCTest", MetricSuite.RoTLoC));
	}

}
