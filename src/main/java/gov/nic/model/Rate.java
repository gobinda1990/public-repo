package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rate {

	private String desc;
	@JsonProperty("tax_val")
	private long taxVal;
	private long cgst;
	private long sgst;
	private long igst;
	private long cess;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getTaxVal() {
		return taxVal;
	}
	public void setTaxVal(long taxVal) {
		this.taxVal = taxVal;
	}
	public long getCgst() {
		return cgst;
	}
	public void setCgst(long cgst) {
		this.cgst = cgst;
	}
	public long getSgst() {
		return sgst;
	}
	public void setSgst(long sgst) {
		this.sgst = sgst;
	}
	public long getIgst() {
		return igst;
	}
	public void setIgst(long igst) {
		this.igst = igst;
	}
	public long getCess() {
		return cess;
	}
	public void setCess(long cess) {
		this.cess = cess;
	}

	
}