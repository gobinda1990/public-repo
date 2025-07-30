package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Signature {

	private String line0;
	private String place;
	@JsonProperty("nameOfTheSignatory")
	private String nameofthesignatory;
	@JsonProperty("membershipNo")
	private String membershipno;
	private String date;
	@JsonProperty("fullAddress")
	private String fulladdress;

	public void setLine0(String line0) {
		this.line0 = line0;
	}

	public String getLine0() {
		return line0;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPlace() {
		return place;
	}

	public void setNameofthesignatory(String nameofthesignatory) {
		this.nameofthesignatory = nameofthesignatory;
	}

	public String getNameofthesignatory() {
		return nameofthesignatory;
	}

	public void setMembershipno(String membershipno) {
		this.membershipno = membershipno;
	}

	public String getMembershipno() {
		return membershipno;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	public String getFulladdress() {
		return fulladdress;
	}

}