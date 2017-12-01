# Samples

### Invoking MetriX
The code below is found at [MainSample.java](MainSample.java).  
```java
public class MainSample {

    public static void main(String[] args) {
        String projecPath = './HelloMetrix/';
        String outputFileName = './HelloMetrix.csv';

        try {
            MetricSuiteExtractor.extract(projecPath, outputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error - Project not found at project path:");
            System.err.println(projecPath);
        }
    }
    
}
```

### Adding console parameters
The code below is found at [MainSampleArgs.java](MainSampleArgs.java).  
```java
public class MainSampleArgs {
    
    public static void main(String[] args) {
        
        if (args.length() > 3)
            tryExtract(args[2], args[3]);

        else if ( args.length() == 3 && !args[1].Equals('MainSampleArgs') )
            tryExtract(args[1], args[2]);

        else
            printInstructions();

	}

    void printInstructions() {
        System.out.println("MainSampleArgs argument error! Correct usage:");
        System.out.println("$ java MainSampleArgs <inputPathFolder> <outputPathCSV>");
    }

    /**
     * A "façade" that invokes MetriX and treats its exception (FileNotFoundException).
     * @param inputPathFolder The project path (required by MetricSuiteExtractor).
	 * @param outputPathCSV The CSV file to output calculated metrics (required by MetricSuiteExtractor).
     */
    void tryExtract (String inputPathFolder, String outputPathCSV) {
        try {
            MetricSuiteExtractor.extract(inputPathFolder, outputPathCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error - Project not found at project path:");
            System.err.println(inputPathFolder);
        }
    }

}
```

[:back:](../../../../)