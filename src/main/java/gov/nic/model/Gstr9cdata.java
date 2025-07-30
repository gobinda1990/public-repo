package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gstr9cdata {

	@JsonProperty("audited_data")
	private AuditedData auditedData;

	private Certificate certificate;

	@JsonProperty("audited_sign")
	private String auditedSign;

	public void setAuditedData(AuditedData auditedData) {
		this.auditedData = auditedData;
	}

	public AuditedData getAuditedData() {
		return auditedData;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setAuditedSign(String auditedSign) {
		this.auditedSign = auditedSign;
	}

	public String getAuditedSign() {
		return auditedSign;
	}

}