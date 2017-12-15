package tests.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Base test class for each metric. Creates test file.
 * @author Flavio Freitas
 *
 */
public class BaseTestManageFile extends BaseTestClearDataAndManageDirectory {
	
	/**
	 * The required test file that will be created.
	 */
	protected String testFilePath;
	
	/**
	 * The source code of created test file.
	 */
	protected String testFileContent;
	
	/**
	 * A PrintWriter to write source code into test file.
	 */
	protected PrintWriter testFilePrintWriter;
	
	/**
	 * The actual test file edited and defined by each child test class.
	 */
	protected File testFile;
	
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
