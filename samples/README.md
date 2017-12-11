<h1 name="metrix-samples">:book: Samples</h1>

### :crystal_ball: Invoking MetriX
The code below is found at [MainSample.java](MainSample.java).  
```java
public static void main(String[] args) {
	String projecPath = "./samples/HelloMetrix";
	String outputFileName = "./HelloMetrix";

	try {
	
		MetricSuiteExtractor.extract(projecPath, outputFileName);
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}
```

### :bar_chart: Results
The script above outputs to the [*root*/data](https://github.com/FlavioFS/MetriX/tree/master/data) folder the *.csv file* represented by the table below :


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


<h3 align="center"><a href="https://github.com/FlavioFS/MetriX/#metrix-home">:back:</a></div>
