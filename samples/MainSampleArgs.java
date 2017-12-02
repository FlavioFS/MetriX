import java.io.FileNotFoundException;

import metrix.MetricSuiteExtractor;

/**
 * A demo for how to invoke MetriX through console (with args).
 * @author Flavio Freitas
 *
 */
public class MainSampleArgs {
    
    public static void main(String[] args) {

        if (args.length > 3)
        	tryExtract(args[2], args[3]);

        else if ( args.length == 3 && !args[1].equals("MainSampleArgs") )
        	tryExtract(args[1], args[2]);

        else
        	printInstructions();

    }

    static void printInstructions() {
        System.out.println("MainSampleArgs argument error! Correct usage:");
        System.out.println("$ java MainSampleArgs <inputPathFolder> <outputPathCSV>");
    }

    /**
     * A "façade" that invokes MetriX and treats its exception (FileNotFoundException).
     * @param inputPathFolder The project path (required by MetricSuiteExtractor).
	 * @param outputPathCSV The CSV file to output calculated metrics (required by MetricSuiteExtractor).
     */
    static void tryExtract (String inputPathFolder, String outputPathCSV) {
        try {
            MetricSuiteExtractor.extract(inputPathFolder, outputPathCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error - Project not found at given path:");
            System.err.println(inputPathFolder);
        }
    }

}