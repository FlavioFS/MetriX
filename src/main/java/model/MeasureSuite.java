package model;

import java.util.Collection;
import java.util.Vector;

/**
 * A data structure for a collection of measures of a class.
 *
 */
public class MeasureSuite {

	/**
	 * Name of measured class. 
	 */
	private String className;

	/**
	 * The collection of metrics obtained from the class.
	 */
	private Collection<Measure> measures;

	/**
	 * Default constructor.
	 * @param className Name of measured class.
	 */
	public MeasureSuite(String className) {
		this.className = className;
		this.measures = new Vector<>();
	}

	/**
	 * Getter for class name.
	 * @return Name of measured class.
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Setter for class name.
	 * @param className Name of measured class.
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Getter for Mmeasure Ccollection.
	 * @return The collection of measures extracted from class *this.className*.
	 */
	public Collection<Measure> getMeasures() {
		return measures;
	}

	/**
	 * Adds measure to the collection of measures extracted from class *this.className*.
	 * @param measure Measure to add.
	 */
	public void addMeasure(Measure measure) {
		if (measures.stream().filter(f -> f.getMetric().getName().equals(measure.getMetric().getName())).count() == 0) {
			this.measures.add(measure);
		}
	}

}
