<h1 name="metrix-samples">Samples</h1>

### Invoking MetriX
The code below is found at [MainSample.java](MainSample.java).  
```java
public static void main(String[] args) {

	System.out.println("Starting Metrix MainSample...");
	
	String projecPath = "./samples/HelloMetrix";
	String outputFileName = "./HelloMetrix";

	try {
		System.out.println("Running extractor...");
		MetricSuiteExtractor.extract(projecPath, outputFileName);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		System.err.println("Error - Project not found at given path:");
		System.err.println(projecPath);
	}

	System.out.println("Done...");
	
}
```

### Results
The script above produces outputs the csv seen below to the [*root*/data](https://github.com/FlavioFS/MetriX/tree/master/data) folder:  

| Property  | Value |
| :------: | :-----------: |
| Classname | HelloMetrix::HelloMetrix |
| RCo | 0.0 |
| HCo | 1.0 |
| SCo | 0.0 |
| NoR | 0.0 |
| NoH | 1.0 |
| NoS | 0.0 |
| NoGH | 1.0 |
| NoEH | 1.0 |
| RoTLoC | 0.3333 |
| RoCLoC | 0.1667 |
| RoFLoC | 0.0 |
| HDoS | 0.0 |
| EHMU | 1.0 |


### An example with console arguments
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

<a href="https://github.com/FlavioFS/MetriX/#metrix-home">:back:</a>