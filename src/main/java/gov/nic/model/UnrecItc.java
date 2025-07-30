package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnrecItc {

	@JsonProperty("itc_avail")
	private long itcAvail;

	public void setItcAvail(long itcAvail) {
		this.itcAvail = itcAvail;
	}

	public long getItcAvail() {
		return itcAvail;
	}

}