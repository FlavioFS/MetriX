package tests.coupling;

import java.io.File;

import junit.framework.Assert;
import metrix.coupling.RCoExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of different thrown exceptions.
 * @author Flavio Freitas
 *
 */
public class RCoTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/RCo/";
		
		testFilePath = "RCoTest.java";
		testFileContent =
			"public class RCoTest {											\n" + 
			"	public void triggerRCo1_0 () {								\n" +
			"		throw new Exception(\"RCo Triggered! (1.0)\");			\n" +
			"	}															\n" +
			"																\n" +
			"	public void triggerRCo1_1 () {								\n" +
			"		throw new Exception(\"RCo Triggered! (1.1)\");			\n" +
			"	}															\n" +
			"																\n" +
			"	public void triggerRCo2 () {								\n" +
			"		throw new IOException(\"RCo Triggered! (2)\");			\n" +
			"	}															\n" +
			"																\n" +
			"	public void triggerRCo3 () {								\n" +
			"		throw new PrinterException(\"RCo Triggered! (3)\");		\n" +
			"	}															\n" +
			"}																\n";
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	public void testRCo() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new RCoExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(3.0, MeasureDataset.getMeasure("RCoTest", MetricSuite.RCo));
	}

}
