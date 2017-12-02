import java.io.FileNotFoundException;

import metrix.MetricSuiteExtractor;

/**
 * A demo for how to invoke MetriX through console.
 * @author Flavio Freitas
 *
 */
public class MainSample {

    public static void main(String[] args) {
    	System.err.println("Starting Metrix MainSample...");
        String projecPath = "./samples/HelloMetrix";
        String outputFileName = "./HelloMetrix";

        try {
        	System.err.println("Running extractor...");
            MetricSuiteExtractor.extract(projecPath, outputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error - Project not found at given path:");
            System.err.println(projecPath);
        }
        System.err.println("Done...");
    }

}