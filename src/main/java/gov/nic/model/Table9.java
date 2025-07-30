package gov.nic.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table9 {

	private List<Rate> rate;
	@JsonProperty("tot_amt_payable")
	private TotAmtPayable totAmtPayable;
	@JsonProperty("unrec_amt")
	private UnrecAmt unrecAmt;
	@JsonProperty("tot_amt_paid")
	private TotAmtPaid totAmtPaid;

	public void setRate(List<Rate> rate) {
		this.rate = rate;
	}

	public List<Rate> getRate() {
		return rate;
	}

	public void setTotAmtPayable(TotAmtPayable totAmtPayable) {
		this.totAmtPayable = totAmtPayable;
	}

	public TotAmtPayable getTotAmtPayable() {
		return totAmtPayable;
	}

	public void setUnrecAmt(UnrecAmt unrecAmt) {
		this.unrecAmt = unrecAmt;
	}

	public UnrecAmt getUnrecAmt() {
		return unrecAmt;
	}

	public void setTotAmtPaid(TotAmtPaid totAmtPaid) {
		this.totAmtPaid = totAmtPaid;
	}

	public TotAmtPaid getTotAmtPaid() {
		return totAmtPaid;
	}

}