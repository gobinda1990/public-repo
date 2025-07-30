package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Certificate {

	@JsonProperty("cert_data")
	private CertData certData;
	
	@JsonProperty("cert_text")
	private CertText certText;

	public void setCertData(CertData certData) {
		this.certData = certData;
	}

	public CertData getCertData() {
		return certData;
	}

	public void setCertText(CertText certText) {
		this.certText = certText;
	}

	public CertText getCertText() {
		return certText;
	}

}