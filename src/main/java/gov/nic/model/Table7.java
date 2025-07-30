package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table7 {
	

	@JsonProperty("annul_turn_adj")
	private long annulTurnAdj;
	
	@JsonProperty("othr_turnovr")
	private long othrTurnovr;
	
	@JsonProperty("zero_sup")
	private long zeroSup;
	
	@JsonProperty("rev_sup")
	private long revSup;
	
	@JsonProperty("tax_turn_annul")
	private long taxTurnAnnul;
	@JsonProperty("tax_turn_adj")
	private long taxTurnAdj;
	@JsonProperty("unrec_tax_turn")
	private long unrecTaxTurn;
	
	
	public long getOthrTurnovr() {
		return othrTurnovr;
	}
	public void setOthrTurnovr(long othrTurnovr) {
		this.othrTurnovr = othrTurnovr;
	}
	public long getZeroSup() {
		return zeroSup;
	}
	public void setZeroSup(long zeroSup) {
		this.zeroSup = zeroSup;
	}
	public long getRevSup() {
		return revSup;
	}
	public void setRevSup(long revSup) {
		this.revSup = revSup;
	}
	public long getAnnulTurnAdj() {
		return annulTurnAdj;
	}
	public void setAnnulTurnAdj(long annulTurnAdj) {
		this.annulTurnAdj = annulTurnAdj;
	}
	public long getTaxTurnAnnul() {
		return taxTurnAnnul;
	}
	public void setTaxTurnAnnul(long taxTurnAnnul) {
		this.taxTurnAnnul = taxTurnAnnul;
	}
	public long getTaxTurnAdj() {
		return taxTurnAdj;
	}
	public void setTaxTurnAdj(long taxTurnAdj) {
		this.taxTurnAdj = taxTurnAdj;
	}
	public long getUnrecTaxTurn() {
		return unrecTaxTurn;
	}
	public void setUnrecTaxTurn(long unrecTaxTurn) {
		this.unrecTaxTurn = unrecTaxTurn;
	}

	

}