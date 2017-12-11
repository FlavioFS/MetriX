package tests.base;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Base test class for each metric. Creates test file.
 * @author Flavio Freitas
 *
 */
public class BaseTestManageFile extends BaseTestClearDataAndManageDirectory {
	
	protected String testFilePath;
	protected String testFileContent;
	protected PrintWriter testFilePrintWriter;
	
	/**
	 * Creates and prints source to file.
	 */
	protected void setUp() {
		super.setUp();
		
		try {
			testFilePrintWriter = new PrintWriter(super.testDirectoryPath + testFilePath, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("FileNotFoundException: Test file not found.");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail("UnsupportedEncodingExceptio: Test file cannot support this encoding.");
		}
		
		testFilePrintWriter.print(testFileContent);
		testFilePrintWriter.close();
	}

	/**
	 * Clears stored measures (calls super).
	 */
	protected void tearDown() {
		super.tearDown();
	}
}
