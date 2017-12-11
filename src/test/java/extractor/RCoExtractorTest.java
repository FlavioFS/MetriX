package extractor;

import java.io.File;

import junit.framework.Assert;
import metrix.coupling.RCoExtractor;
import model.MeasureDataset;
import model.MetricSuite;
import util.DirExplorer;
import util.JavaFilter;
import util.SingleMetricFileHandler;

public class RCoExtractorTest extends BaseTestManageFile {

	File testFile;
	
	protected void setUp() {
		testDirectoryPath = "./.tests/RCo/";
		
		testFilePath = "RCoTest.java";
		testFileContent =
			"public class RCoTest {											\n" + 
			"	public void triggerRCo1 () throws Exception {				\n" +
			"		throw new Exception(\"RCo Triggered! (1)\");			\n" +
			"	}															\n" +
			"																\n" +
			"	public void triggerRCo2 () throws IOException {				\n" +
			"		throw new IOException(\"RCo Triggered! (2)\");			\n" +
			"	}															\n" +
			"																\n" +
			"	public void triggerRCo3 () throws NullPointerException {	\n" +
			"		throw new NullPointerException(\"RCo Triggered! (3)\");	\n" +
			"	}															\n" +
			"}																\n";
		
		super.setUp();
	}

	protected void tearDown() {
		super.tearDown();
	}
	
	public void testRCo() {
		
		File projectDir = new File(testDirectoryPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler sourceFileHandler = new SingleMetricFileHandler(projectDir, new RCoExtractor());
		
		new DirExplorer(javaFilter, sourceFileHandler).explore(projectDir);		
		
		Assert.assertEquals(3.0, MeasureDataset.getMeasure("RCoTest", MetricSuite.RCo));
	}

}
