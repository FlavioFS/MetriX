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
	
	/**
	 * Forces Coupling metrics.
	 * @throws IOException SCo++ (SCo == 1.0)
	 * @throws PrinterException SCo++ (SCo == 2.0)
	 * @throws Exception SCo++ (SCo == 3.0)
	 */
	public void coupling () throws IOException, PrinterException, Exception {
		
		// HCo++ (HCo == 1.0)
		try {
		} catch (NullPointerException e) {
			
		} finally {
			
		}
		
		// HCo++ (HCo == 2.0)
		try {
			fakeAttribute++;
		} catch (Exception e) {
			fakeAttribute--;
		}
		
		// RCo++ (RCo == 1.0)
		throw new IOException("");
	}
	
	/*
	 * This section forces results for Size metrics to be different from results for Coupling metrics.
	 */
	public void sizeCount01 () { }
	public void sizeCount02 () { }
	public void sizeCount03 () { }
	public void sizeCount04 () { }
	public void sizeCount05 () { }
	public void sizeCount06 () { }
	public void sizeCount07 () { }
	public void sizeCount08 () { }
	public void sizeCount09 () { }
	public void sizeCount10 () { }
	
}
