package tests.coupling;

import java.io.File;

import junit.framework.Assert;
import metrix.coupling.HCoExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

/**
 * Amount of different exceptions in catch blocks.
 * @author Flavio Freitas
 *
 */
public class HCoTest extends BaseTestManageFile {

File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/HCo/";
		
		testFilePath = "HCoTest.java";
		testFileContent =
			"public class HCoTest {					\n" + 
			"	public void triggerHCo () {			\n" +
			"		try {}							\n" +
			"		catch (IOException e) {}		\n" +
			"		catch (PrinterException e) {}	\n" +
			"	}									\n" +
			"										\n" +
			"	public void repeatExceptions () {	\n" +
			"		try {}							\n" +
			"		catch (IOException e) {}		\n" +
			"		catch (PrinterException e) {}	\n" +
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
	
	public void testRCo() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new HCoExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(2.0, MeasureDataset.getMeasure("HCoTest", MetricSuite.HCo));
	}

}
