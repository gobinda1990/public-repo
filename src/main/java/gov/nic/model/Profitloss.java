package gov.nic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profitloss {

	@JsonProperty("doc_nam")
	private String docNam;
	@JsonProperty("doc_id")
	private String docId;

	public void setDocNam(String docNam) {
		this.docNam = docNam;
	}

	public String getDocNam() {
		return docNam;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocId() {
		return docId;
	}

}