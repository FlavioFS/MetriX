package main;

import java.io.FileNotFoundException;

import metrix.Metrix;

/**
 * A demo for how to invoke MetriX through console.
 * @author Flavio Freitas
 *
 */
public class MainSample {

    public static void main(String[] args) {
    	
        String projecPath = "./samples/HelloMetrix";
        String outputFileName = "./HelloMetrix";

        try {
        	System.out.println("MetriX: extracting metrics...");
            
        	// Calls MetriX
        	Metrix.extract(projecPath, outputFileName);
        	
        	System.out.println("MetriX: Done! (check \".data/\" folder)");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("MetriX: Error - Project not found at given path:");
            System.err.println(projecPath);
        }
        
        
    }

}