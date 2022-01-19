package fr.eql.eqlXchange.matchEngine.constant;


public enum Interval {

	Minute("1m",1),
	ThreeMinutes("3m",3),
	FiveMinutes ("5m",5),
	FifteenMinutes("15m",15),
	ThirtyMinutes("30m",30),
	Hour        ("1h",60),
	TwoHours    ("2h",120),
	FourHours   ("4h", 240),
	SixHours    ("6h",360),
	EightHours  ("8h",8*60),
	TwelveHours ("12h",12*60),
	Day         ("1d",24*60),
	ThreeDays   ("3d",72*60),
	Week        ("1w",7*24*60),
	Month       ("1M",30*24*60);

	public final String label;
	public final long numberOfMinute;

	private Interval(String label, long numberOfMinute) {
		this.label = label;
		this.numberOfMinute = numberOfMinute;
	}

}
