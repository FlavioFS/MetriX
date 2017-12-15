package tests.size;

import java.io.File;

import junit.framework.Assert;
import metrix.size.NoHExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of catch blocks divided by amount of methods and constructors.
 * @author Flavio Freitas
 *
 */
public class NoHTest extends BaseTestManageFile {

	/**
	 * Creates a test class for NoH metric.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/NoH/";
		
		testFilePath = "NoHTest.java";
		testFileContent =
			"public class NoHTest {								\n" + 
			"	public void NoHTest () {}						\n" +
			"													\n" +
			"	public void triggerNoH () throws Exception {	\n" +
			"		try {}										\n" +
			"		catch (IOException e) {}					\n" +
			"		catch (PrinterException e) {}				\n" +
			"	}												\n" +
			"													\n" +
			"	public void sizeCount01 () {}					\n" +
			"	public void sizeCount02 () {}					\n" +
			"	public void sizeCount03 () {}					\n" +
			"	public void sizeCount04 () {}					\n" +
			"	public void sizeCount05 () {}					\n" +
			"	public void sizeCount06 () {}					\n" +
			"}													\n";
				
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	/**
	 * Tests NoH metric using its test file.
	 */
	public void testRCo() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new NoHExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(2.0/8, MeasureDataset.getMeasure("NoHTest", MetricSuite.NoH));
	}

}
