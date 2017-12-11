package tests.ratio;

import java.io.File;

import junit.framework.Assert;
import metrix.ratio.RoFLoCExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of finally lines divided by amount of lines in class.
 * @author Flavio Freitas
 *
 */
public class RoFLoCTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/RoFLoC/";
		
		testFilePath = "RoFLoCTest.java";
		testFileContent =
			"public class RoFLoCTest {				\n" +	// 1
			"	public void triggerRoFLoC () {		\n" +
			"		try {}							\n" +
			"		catch (IOException e) {}		\n" +
			"		finally{						\n" +		// 5
			"										\n" +
			"										\n" +
			"										\n" +
			"		}								\n" +		// 9
			"	}									\n" +
			"}										\n";	// 11
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	public void testRoFLoC() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new RoFLoCExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals( (9.0-5.0)/(11-1), MeasureDataset.getMeasure("RoFLoCTest", MetricSuite.RoFLoC) );
	}

}
