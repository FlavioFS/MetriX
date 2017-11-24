package model;

public enum MetricSuite {
	RCo("RCo"), SCo("SCo"), HCo("HCo"), NoR("NoR"), NoS("NoS"), NoH("NoH"), RoTLoC("RoTLoC"), RoCLoC("RoCLoC"), RoFLoC(
			"RoFLoC"), HDoS("HDoS"), EHMU("EHMU"), NoGH("NoGH"), NoEH("NoEH");

	private final String name;

	MetricSuite(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
