package tests.usage;

import java.io.File;

import junit.framework.Assert;
import metrix.usage.EHMUExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

public class EHMUTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/EHMU/";
		
		testFilePath = "EHMUTest.java";
		testFileContent =
			"public class EHMUTest0 {				\n" +
			"	public void noTriggerEHMU () {}		\n" +
			"}										\n" +
			"										\n" +
			"public class EHMUTest1 {				\n" +
			"	public void triggerEHMU () {		\n" +
			"		try {}							\n" +
			"		catch (IOException e) {}		\n" +
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
	
	public void testEHMU() {
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new EHMUExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(0.0, MeasureDataset.getMeasure("EHMUTest0", MetricSuite.EHMU));
		Assert.assertEquals(1.0, MeasureDataset.getMeasure("EHMUTest1", MetricSuite.EHMU));
	}

}
