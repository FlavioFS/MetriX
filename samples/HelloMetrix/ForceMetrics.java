package HelloMetrix;

import java.awt.print.PrinterException;
import java.io.IOException;

/**
 * Sample class to force occurrence of each metric.
 * @author Flavio Freitas
 *
 */
public class ForceMetrics {
	int fakeAttribute;
	
	public ForceMetrics () {
		try {
		} catch (NullPointerException e) {
			
		} finally {
			
		}
	}
	
	public void fakeMethod () throws IOException, PrinterException, Exception {
		try {
			fakeAttribute++;
		} catch (Exception e) {
			fakeAttribute--;
		}
		
		throw new IOException("");
	}
	
	public void fakeMethod2 () {
		try {
			fakeAttribute++;
		} catch (Exception e) {
		} finally {
			
		}		
	}
	
	public void sizeCount01 () { }
	public void sizeCount02 () { }
	public void sizeCount03 () { }
	public void sizeCount04 () { }
	public void sizeCount05 () { }
	public void sizeCount06 () { }
	public void sizeCount07 () { }
	
}
