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

public class MeasureDataset {

	private final static String DATASET_PATH = "./data";

	private static Map<String, MeasureSuite> dataset = new HashMap<>();

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

	public static Collection<MeasureSuite> list() {
		return dataset.values();
	}

	public static String toCSV() {
		StringBuffer buffer = new StringBuffer("classname");
		
		for (MeasureSuite suite : list()) {
			buffer.append(",");
			buffer.append(suite.getClassName().replace(".", "::"));
			
			for (Measure measure : suite.getMeasures()) {				
				buffer.append("\n");
				buffer.append(measure.getMetric().getName());
				buffer.append(",");
				buffer.append(measure.getValue());
			}
			break;
		}

		return buffer.toString();
	}

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
