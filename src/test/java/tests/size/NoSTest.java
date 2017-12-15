package tests.size;

import java.io.File;

import junit.framework.Assert;
import metrix.size.NoSExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of methods signaling exceptions divided by amount of methods and constructors.
 * @author Flavio Freitas
 *
 */
public class NoSTest extends BaseTestManageFile {

	/**
	 * Creates a test class for NoS metric.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/NoS/";
		
		testFilePath = "NoSTest.java";
		testFileContent =
			"public class NoSTest {										\n" +
			"	public void NoSTest () {}								\n" +
			"															\n" +
			"	public void triggerNoS1 () throws Exception {}			\n" +
			"	public void triggerNoS2 () throws IOException {}		\n" +
			"	public void triggerNoS3 () throws PrinterException {}	\n" +
			"	public void triggerNoS3 () throws AWTException {}		\n" +
			"															\n" +
			"	public void sizeCount01 () {}							\n" +
			"	public void sizeCount02 () {}							\n" +
			"	public void sizeCount03 () {}							\n" +
			"}															\n";
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	/**
	 * Tests NoS metric using its test file.
	 */
	public void testNoS() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new NoSExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(4.0/8, MeasureDataset.getMeasure("NoSTest", MetricSuite.NoS));
	}

}
