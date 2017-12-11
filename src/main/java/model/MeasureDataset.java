package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A data structure to store Measures for each input class in an internal collection.
 *
 */
public class MeasureDataset {

	private final static String DATASET_PATH = "./data";

	private static Map<String, MeasureSuite> dataset = new HashMap<>();

	/**
	 * Adds a measure to the measure collection of a class (creates new collection when key not found).
	 * @param className (Key) Name of this class.
	 * @param measure (Value) Added measure.
	 */
	public static void store(String className, Measure measure) {
		MeasureSuite suite = null;
		if (dataset.containsKey(className)) {
			suite = dataset.get(className);
		} else {
			suite = new MeasureSuite(className);
			dataset.put(className, suite);
		}
		suite.addMeasure(measure);
	}
	
	/**
	 * Clears stored measures, ALL of them.
	 */
	public static void clear() {
		dataset.clear();
	}
	
	/**
	 * Gets requested measure for specified class.
	 * @param className The class to search.
	 * @param metric The metric to get.
	 * @return Metric value for the class.
	 */
	public static Double getMeasure (String className, MetricSuite metric) {
		if (dataset.containsKey(className)) {
			MeasureSuite suite = dataset.get(className);
			for (Measure m : suite.getMeasures()) {
				if (m.getMetric().equals(metric)) {
					return m.getValue(); 
				}
			}
		}
		return -1.0;
	}

	/**
	 * Getter for a collection of statistics gathered from each class.
	 * @return Collection of MeasureSuite's (each element represents one class).
	 */
	static Collection<MeasureSuite> list() {
		return dataset.values();
	}

	/**
	 * Translates metrics from data structures to a CSV table. 
	 * @return A CSV table as a string.
	 */
	static String toCSV() {
		StringBuffer buffer = new StringBuffer("");
		
		appendHeader(buffer);
		appendClasses(buffer);
		
		return buffer.toString(); 	
	}

	/**
	 * Adds the CSV header text (line of labels) to buffer.
	 * @param buffer CSV text being constructed.
	 */
	static void appendHeader(StringBuffer buffer) {
		buffer.append("classname");
		for (MeasureSuite suite : list()) {
			for (Measure measure : suite.getMeasures()) {
				buffer.append(",");
				buffer.append(measure.getMetric().getName());
			}
			break;
		}
	}
		
	/**
	 * Adds CSV body text (lines of classes and their metrics) to buffer.
	 * @param buffer CSV text being constructed.
	 */
	static void appendClasses (StringBuffer buffer) {
		for (MeasureSuite suite : list()) {
			buffer.append("\n");
			buffer.append(suite.getClassName().replace(".", "::"));
			
			for (Measure measure : suite.getMeasures()) {
				buffer.append(",");
				buffer.append(measure.getValue());
			}
		}
	}
	
	/**
	 * Creates and saves CSV file to DATASET_PATH directory (./data)
	 * @param fileName Path to directory containing the input project.
	 */
	public static void generateCSVFile(String fileName) {
		File directory = new File(DATASET_PATH);
		if (!directory.exists()) {
			directory.mkdir();
		}

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		dateFormat.setTimeZone(calendar.getTimeZone());
		StringBuffer reportName = new StringBuffer(fileName + "-EHMS-");
		reportName.append(dateFormat.format(calendar.getTime()));
		reportName.append(".csv");
		String fullReportName = reportName.toString();

		File reportFile = new File(directory, fullReportName);
		try {
			FileWriter fileWriter = new FileWriter(reportFile, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.append(toCSV());
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
