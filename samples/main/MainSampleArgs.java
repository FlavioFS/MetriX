package main;

import java.io.FileNotFoundException;

import metrix.Metrix;

/**
 * A demo for how to invoke MetriX through console (with args).
 * @author Flavio Freitas
 *
 */
public class MainSampleArgs {
    
    public static void main(String[] args) {
    	
        if (args.length == 2)
        	tryExtract(args[0], args[1]);
        else
        	printInstructions();

    }

    static void printInstructions() {
        System.out.println("MetriX: MainSampleArgs argument error! Correct usage:");
        System.out.println("$ java MainSampleArgs <inputPathFolder> <outputPathCSV>");
    }

    /**
     * A "facade" that invokes MetriX and treats its exception (FileNotFoundException).
     * @param inputPathFolder The project path (required by MetricSuiteExtractor).
	 * @param outputPathCSV The CSV file to output calculated metrics (required by MetricSuiteExtractor).
     */
    static void tryExtract (String inputPathFolder, String outputPathCSV) {
        try {
        	System.out.println("MetriX: extracting metrics...");
            Metrix.extract(inputPathFolder, outputPathCSV);
            System.out.println("MetriX: Done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("MetriX: Error - Project not found at given path:");
            System.err.println(inputPathFolder);
        }
    }

}