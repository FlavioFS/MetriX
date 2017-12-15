package tests.concern;

import java.io.File;

import junit.framework.Assert;
import metrix.concern.HDoSExtractor;
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
public class HDoSTest extends BaseTestManageFile {

	/**
	 * Creates a test class for HDoS metric.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/HDoS/";
		
		testFilePath = "HDoSTest.java";
		testFileContent =
				"public class HDoSTest {				\n" + 
				"	public void HDoSTest () {			\n" +
				"		try {}							\n" +
				"		catch (PrinterException e) {	\n" +
				"		}								\n" +
				"		catch (AWTException e) {		\n" +
				"										\n" +
				"										\n" +
				"		}								\n" +
				"	}									\n" +
				"										\n" +
				"	public void triggerHDoS2 () {		\n" +
				"		try {}							\n" +
				"		catch (IOException e) {			\n" +
				"		}								\n" +
				"		catch (Exception e) {			\n" +
				"										\n" +
				"		}								\n" +
				"	}									\n" +
				"}										\n";
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	/**
	 * Tests HDoS metric using its test file.
	 */
	public void testHDoS() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new HDoSExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(0.9796, MeasureDataset.getMeasure("HDoSTest", MetricSuite.HDoS));
	}

}
