package tests.size;

import java.io.File;

import junit.framework.Assert;
import metrix.size.NoGHExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of Generic Handlers. Accounts only for generic exceptions (precisely typeof Exception).
 * @author Flavio Freitas
 *
 */
public class NoGHTest extends BaseTestManageFile {

	/**
	 * Creates a test class for NoGH metric.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/NoGH/";
		
		testFilePath = "NoGHTest.java";
		testFileContent =
			"public class NoGHTest {				\n" + 
			"	public void triggerNoGH () {		\n" +
			"		try {}							\n" +
			"		catch (Exception e) {}			\n" +
			"		catch (IOException e) {}		\n" +
			"		catch (PrinterException e) {}	\n" +
			"		catch (AWTException e) {}		\n" +
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
	 * Tests NoGH metric using its test file.
	 */
	public void testRCo() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new NoGHExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(1.0, MeasureDataset.getMeasure("NoGHTest", MetricSuite.NoGH));
	}

}
