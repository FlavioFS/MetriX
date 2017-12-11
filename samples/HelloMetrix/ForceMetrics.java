package HelloMetrix;

/**
 * Sample class to force occurrence of each metric.
 * @author Flavio Freitas
 *
 */
public class ForceMetrics {
	int fakeAttribute;
	
	public ForceMetrics (int fakeAttribute) {
		this.fakeAttribute = fakeAttribute;
	}
	
	public void coupling () {
		try {
			
		} catch (Exception e) {
			
		}
		
		try {
			
		} catch (Exception e) {
			
		}
		
		try {
			fakeAttribute++;
		} catch (Exception e) {
			fakeAttribute--;
		}
	}
	
	public void increment () {
		fakeAttribute++;
	}
	
	public void decrement () {
		fakeAttribute--;
		
		
		try {
			
		} catch (Exception e) {
			
		}
		
		try {
			
		} catch (Exception e) {
			
		}

		try {
			
		} catch (Exception e) {
			
		}
		
	}
}
