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


| Classname | RCo | HCo | SCo | NoR | NoH | NoS | NoGH | NoEH | RoTLoC | RoCLoC | RoFLoC | HDoS | EHMU |
| :-------: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: | :-----: |
| HelloMetrix::ForceMetrics | 1.0 | 2.0 | 3.0 | 1.0 | 2.0 | 3.0 | 1.0 | 1.0 | 0.3889 | 0.2222 | 0.0 | 0.0 | 1.0 |
| HelloMetrix::HelloMetrix  | 0.0 | 1.0 | 0.0 | 0.0 | 1.0 | 0.0 | 1.0 | 1.0 | 0.3333 | 0.1667 | 0.0 | 0.0 | 1.0 |

<h3 align="center"><a href="https://github.com/FlavioFS/MetriX/#metrix-home">:back:</a></div>
