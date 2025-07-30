package gov.nic.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table14 {

	private List<Items> items;
	@JsonProperty("tot_elig_itc")
	private TotEligItc totEligItc;
	@JsonProperty("itc_claim")
	private ItcClaim itcClaim;
	@JsonProperty("unrec_itc")
	private UnrecItc unrecItc;

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setTotEligItc(TotEligItc totEligItc) {
		this.totEligItc = totEligItc;
	}

	public TotEligItc getTotEligItc() {
		return totEligItc;
	}

	public void setItcClaim(ItcClaim itcClaim) {
		this.itcClaim = itcClaim;
	}

	public ItcClaim getItcClaim() {
		return itcClaim;
	}

	public void setUnrecItc(UnrecItc unrecItc) {
		this.unrecItc = unrecItc;
	}

	public UnrecItc getUnrecItc() {
		return unrecItc;
	}

}