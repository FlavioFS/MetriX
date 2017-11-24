package model;

public class Measure {

	private MetricSuite metric;

	private Double value;

	public Measure(MetricSuite metric, Double value) {
		this.metric = metric;
		this.value = value;
	}

	public MetricSuite getMetric() {
		return metric;
	}

	public Double getValue() {
		return value;
	}

}
