package model;

/**
 * A data structure to store a metric and its value.
 * @author Flavio Freitas
 *
 */
public class Measure {

	/**
	 * Stored metric as enum.
	 */
	private MetricSuite metric;

	/**
	 * Value of stored metric.
	 */
	private Double value;

	/**
	 * Full constructor.
	 * @param metric Stored metric.
	 * @param value Value of stored metric.
	 */
	public Measure(MetricSuite metric, Double value) {
		this.metric = metric;
		this.value = value;
	}

	/**
	 * Getter for stored metric enum.
	 * @return Stored metric type (enum).
	 */
	public MetricSuite getMetric() {
		return metric;
	}

	/**
	 * Getter for stored metric's value.
	 * @return Stored metric's value.
	 */
	public Double getValue() {
		return value;
	}

}
