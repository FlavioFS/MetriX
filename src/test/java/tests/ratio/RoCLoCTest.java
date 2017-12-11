package tests.ratio;

import java.io.File;

import junit.framework.Assert;
import metrix.ratio.RoCLoCExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of catch lines divided by amount of lines in class.
 * @author Flavio Freitas
 *
 */
public class RoCLoCTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/RoCLoC/";
		
		testFilePath = "RoCLoCTest.java";
		testFileContent =
			"public class RoCLoCTest {				\n" +	// 1
			"										\n" +
			"	public void triggerRoCLoC () {		\n" +
			"										\n" +
			"		try {}							\n" +
			"		catch (IOException e) {			\n" +		// 6
			"		}								\n" +		// 7 	
			"										\n" +
			"	}									\n" +
			"										\n" +
			"}										\n";	// 11
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	public void testRoCLoC() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new RoCLoCExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals( (7.0-6.0)/(11-1), MeasureDataset.getMeasure("RoCLoCTest", MetricSuite.RoCLoC) );
	}

}
