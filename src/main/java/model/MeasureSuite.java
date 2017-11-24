package model;

import java.util.Collection;
import java.util.Vector;

public class MeasureSuite {

	private String className;

	private Collection<Measure> measures;

	public MeasureSuite(String className) {
		this.className = className;
		this.measures = new Vector<>();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Collection<Measure> getMeasures() {
		return measures;
	}

	public void addMeasure(Measure measure) {
		if (measures.stream().filter(f -> f.getMetric().getName().equals(measure.getMetric().getName())).count() == 0) {
			this.measures.add(measure);
		}
	}

}
