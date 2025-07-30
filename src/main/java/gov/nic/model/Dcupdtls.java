package gov.nic.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dcupdtls {

	@JsonProperty("balance_sheet")
	private List<BalanceSheet> balanceSheet;
	private List<Profitloss> profitloss;

	public void setBalanceSheet(List<BalanceSheet> balanceSheet) {
		this.balanceSheet = balanceSheet;
	}

	public List<BalanceSheet> getBalanceSheet() {
		return balanceSheet;
	}

	public void setProfitloss(List<Profitloss> profitloss) {
		this.profitloss = profitloss;
	}

	public List<Profitloss> getProfitloss() {
		return profitloss;
	}

}