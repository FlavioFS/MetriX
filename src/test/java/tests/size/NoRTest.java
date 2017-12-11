package tests.size;

import java.io.File;

import junit.framework.Assert;
import metrix.size.NoRExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of exceptions thrown divided by amount of constructors and methods.
 * @author Flavio Freitas
 *
 */
public class NoRTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/NoR/";
		
		testFilePath = "NoRTest.java";
		testFileContent =
			"public class NoRTest {								\n" +
			"	public void NoRTest () {}						\n" +
			"													\n" +
			"	public void triggerNoR () {						\n" +
			"		throw new Exception(\"NoR Triggered!\");	\n" +
			"	}												\n" +
			"													\n" +
			"	public void sizeCount01 () {}					\n" +
			"	public void sizeCount02 () {}					\n" +
			"	public void sizeCount03 () {}					\n" +
			"}													\n";
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	public void testNoR() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new NoRExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(1.0/5, MeasureDataset.getMeasure("NoRTest", MetricSuite.NoR));
	}
	
}
