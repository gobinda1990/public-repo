package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CertText {

	@JsonProperty("cert_text_partb1")
	private CertTextPartb1 certTextPartb1;

	@JsonProperty("cert_text_partb2")
	private CertTextPartb2 certTextPartb2;

	public CertTextPartb1 getCertTextPartb1() {
		return certTextPartb1;
	}

	public void setCertTextPartb1(CertTextPartb1 certTextPartb1) {
		this.certTextPartb1 = certTextPartb1;
	}

	public CertTextPartb2 getCertTextPartb2() {
		return certTextPartb2;
	}

	public void setCertTextPartb2(CertTextPartb2 certTextPartb2) {
		this.certTextPartb2 = certTextPartb2;
	}

}