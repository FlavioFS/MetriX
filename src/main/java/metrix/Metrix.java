package metrix;

import java.io.File;
import java.io.FileNotFoundException;

import model.MeasureDataset;
import util.AllMetricsFileHandler;
import util.DirExplorer;
import util.JavaFilter;

/**
 * Extracts ALL available metrics.
 *
 */
public class Metrix {

	/**
	 * Applies all metrics to input project then outputs results to a CSV file.
	 * @param projecPath The path to all source files, including sub-directories.
	 * @param outputFileName The CSV file to output calculated metrics.
	 * @throws FileNotFoundException Thrown when input project is not found.
	 */
	public static void extract(String projecPath, String outputFileName) throws FileNotFoundException {
		
		File projectDir = new File(projecPath);
		
		DirExplorer.Filter javaFilter = new JavaFilter();
		DirExplorer.FileHandler completeFileHandler = new AllMetricsFileHandler(projectDir);
		
		new DirExplorer(javaFilter, completeFileHandler).explore(projectDir);
		MeasureDataset.generateCSVFile(outputFileName);
		
	}
	
}
