package tests.base;

import java.io.File;

import junit.framework.TestCase;
import model.MeasureDataset;

/**
 * Base test class for each metric. Clears measures and manages test directory.
 * @author Flavio Freitas
 *
 */
public class BaseTestClearDataAndManageDirectory extends TestCase {
	
	/**
	 * The path to test directory that will be created during test exectuion.
	 */
	protected String testDirectoryPath = "./tests/";
	
	/**
	 * The created test directory. It will be used by each metric test.
	 */
	protected File testDirectory;
	
	
	/**
	 * Clears stored measures and creates test directory.
	 */
	protected void setUp() {
		MeasureDataset.clear();
		
		testDirectory = new File (testDirectoryPath);
		testDirectory.mkdirs();
		
		if (!testDirectory.exists()) {
			fail("Test directory does not exist and cannot be created.");
		}
	}

	/**
	 * Clears stored measures.
	 */
	protected void tearDown() {
		MeasureDataset.clear();
	}

}
