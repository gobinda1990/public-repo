package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table12 {

	@JsonProperty("itc_avail")
	private long itcAvail;
	@JsonProperty("itc_book_earl")
	private long itcBookEarl;
	@JsonProperty("itc_book_curr")
	private long itcBookCurr;
	@JsonProperty("itc_avail_audited")
	private long itcAvailAudited;
	@JsonProperty("itc_claim")
	private long itcClaim;
	@JsonProperty("unrec_itc")
	private long unrecItc;

	public long getItcAvail() {
		return itcAvail;
	}

	public void setItcAvail(long itcAvail) {
		this.itcAvail = itcAvail;
	}

	public long getItcBookEarl() {
		return itcBookEarl;
	}

	public void setItcBookEarl(long itcBookEarl) {
		this.itcBookEarl = itcBookEarl;
	}

	public long getItcBookCurr() {
		return itcBookCurr;
	}

	public void setItcBookCurr(long itcBookCurr) {
		this.itcBookCurr = itcBookCurr;
	}

	public long getItcAvailAudited() {
		return itcAvailAudited;
	}

	public void setItcAvailAudited(long itcAvailAudited) {
		this.itcAvailAudited = itcAvailAudited;
	}

	public long getItcClaim() {
		return itcClaim;
	}

	public void setItcClaim(long itcClaim) {
		this.itcClaim = itcClaim;
	}

	public long getUnrecItc() {
		return unrecItc;
	}

	public void setUnrecItc(long unrecItc) {
		this.unrecItc = unrecItc;
	}

}