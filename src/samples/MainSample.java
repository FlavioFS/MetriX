package metrix;

/**
 * A demo for how to invoke MetriX through console.
 * @author Flavio Freitas
 *
 */
public class MainSample {
    
    public static void main(String[] args) {
        String projecPath = './HelloMetrix/';
        String outputFileName = './HelloMetrix.csv';

        try {
            MetricSuiteExtractor.extract(projecPath, outputFileName);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.err.println("Error - Project not found at project path:");
            System.err.println(projecPath);
        }
	}

}