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

public class NoHTest extends BaseTestManageFile {

File testFile;
	
	/**
	 * Creates test file.
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
	
	public void testRCo() {
		
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new NoHExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(0.25, MeasureDataset.getMeasure("NoHTest", MetricSuite.NoH));
	}

}
