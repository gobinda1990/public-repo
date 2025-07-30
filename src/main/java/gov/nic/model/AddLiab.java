package gov.nic.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddLiab {

	private String place;
	private String signatory;
	@JsonProperty("mem_no")
	private String memNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date date;
	@JsonProperty("audit_addr")
	private AuditAddr auditAddr;
	@JsonProperty("pan_no")
	private String panNo;

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPlace() {
		return place;
	}

	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}

	public String getSignatory() {
		return signatory;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setAuditAddr(AuditAddr auditAddr) {
		this.auditAddr = auditAddr;
	}

	public AuditAddr getAuditAddr() {
		return auditAddr;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPanNo() {
		return panNo;
	}

}