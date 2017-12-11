package extractor;

import java.io.File;

import junit.framework.Assert;
import metrix.coupling.HCoExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

public class HCoTest extends BaseTestManageFile {

File testFile;
	
	/**
	 * Creates test file.
	 */
	protected void setUp() {
		testDirectoryPath = "./.tests/HCo/";
		
		testFilePath = "HCoTest.java";
		testFileContent =
			"public class HCoTest {								\n" + 
			"	public void triggerHCo () throws Exception {	\n" +
			"		try {										\n" +
			"		}											\n" +
			"		catch (IOException e) {						\n" +
			"		}											\n" +
			"		catch (PrinterException e) {				\n" +
			"		}											\n" +
			"	}												\n" + 
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
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new HCoExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(2.0, MeasureDataset.getMeasure("HCoTest", MetricSuite.HCo));
	}

}
