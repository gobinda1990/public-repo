package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Section3 {

	@JsonProperty("sec_3a")
	private Sec3a sec3a;
	@JsonProperty("sec_3b")
	private Sec3b sec3b;

	public void setSec3a(Sec3a sec3a) {
		this.sec3a = sec3a;
	}

	public Sec3a getSec3a() {
		return sec3a;
	}

	public void setSec3b(Sec3b sec3b) {
		this.sec3b = sec3b;
	}

	public Sec3b getSec3b() {
		return sec3b;
	}

}