package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {

	private String desc;
	private long val;
	@JsonProperty("itc_amt")
	private long itcAmt;
	@JsonProperty("itc_avail")
	private long itcAvail;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getVal() {
		return val;
	}

	public void setVal(long val) {
		this.val = val;
	}

	public long getItcAmt() {
		return itcAmt;
	}

	public void setItcAmt(long itcAmt) {
		this.itcAmt = itcAmt;
	}

	public long getItcAvail() {
		return itcAvail;
	}

	public void setItcAvail(long itcAvail) {
		this.itcAvail = itcAvail;
	}

}