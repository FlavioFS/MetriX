package tests.size;

import java.io.File;

import junit.framework.Assert;
import metrix.size.NoEHExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of Empty Handlers: catch blocks with no content inside.
 * @author Flavio Freitas
 *
 */
public class NoEHTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/NoEH/";
		
		testFilePath = "NoEHTest.java";
		testFileContent =
			"public class NoEHTest {						\n" +
			"	int fakeVar = 0;							\n" +
			"	public void triggerNoEH () {				\n" +
			"		try {}									\n" +
			"		catch (Exception e) {}					\n" +
			"		catch (IOException e) {	fakeVar++; }	\n" + 
			"		catch (PrinterException e) {}			\n" +
			"		catch (AWTException e) {}				\n" +
			"	}											\n" +
			"}												\n";
				
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
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new NoEHExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(3.0, MeasureDataset.getMeasure("NoEHTest", MetricSuite.NoEH));
	}

}
