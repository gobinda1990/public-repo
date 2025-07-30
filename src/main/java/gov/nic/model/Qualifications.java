package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Qualifications {

	@JsonProperty("qual_type")
	private String qualType;
	private String value;

	public void setQualType(String qualType) {
		this.qualType = qualType;
	}

	public String getQualType() {
		return qualType;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}