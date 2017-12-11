package tests.coupling;

import java.io.File;

import junit.framework.Assert;
import metrix.coupling.SCoExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import tests.base.BaseTestManageFile;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

public class SCoTest extends BaseTestManageFile {

	File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/SCo/";
		
		testFilePath = "SCoTest.java";
		testFileContent =
			"public class SCoTest {										\n" + 
			"	public void triggerSCo1 () throws Exception {}			\n" +
			"	public void triggerSCo2 () throws IOException {}		\n" +
			"	public void triggerSCo3 () throws PrinterException {}	\n" +
			"	public void triggerSCo3 () throws AWTException {}		\n" +
			"}															\n";
		
		super.setUp();
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		super.tearDown();
	}
	
	public void testSCo() {
		
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new SCoExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(4.0, MeasureDataset.getMeasure("SCoTest", MetricSuite.SCo));
	}

}
