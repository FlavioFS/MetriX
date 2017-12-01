package metrix;

/**
 * A MetriX-style "Hello World" for documentation purposes.
 * @author Flavio Freitas
 *
 */
public class HelloMetrix {
    
    public static void main(String[] args) {
        String projecPath = './HelloMetrix';
        String outputFileName = './HelloMetrix.csv';
        MetricSuiteExtractor.extract(projecPath, outputFileName);
	}

}