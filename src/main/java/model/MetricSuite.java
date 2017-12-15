package model;

/**
 * Represents the list of metrics provided by MetriX.
 * @author Flavio Freitas
 *
 */
public enum MetricSuite {
	RCo("RCo"), SCo("SCo"), HCo("HCo"), NoR("NoR"), NoS("NoS"), NoH("NoH"), RoTLoC("RoTLoC"), RoCLoC("RoCLoC"), RoFLoC(
			"RoFLoC"), HDoS("HDoS"), EHMU("EHMU"), NoGH("NoGH"), NoEH("NoEH");

	/**
	 * Metric name.
	 */
	private final String name;

	/**
	 * Metric enum constructor.
	 * @param name
	 */
	MetricSuite(String name) {
		this.name = name;
	}

	/**
	 * Getter for metric name.
	 * @return Metric name.
	 */
	public String getName() {
		return name;
	}
}
