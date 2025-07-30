
package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddAddr {

	private String bno;
	private String fno;
	private String building;
	private String road;
	private String city;
	private String district;
	private String state;
	@JsonProperty("pin_code")
	private int pinCode;

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getBno() {
		return bno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getFno() {
		return fno;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuilding() {
		return building;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getRoad() {
		return road;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public int getPinCode() {
		return pinCode;
	}

}