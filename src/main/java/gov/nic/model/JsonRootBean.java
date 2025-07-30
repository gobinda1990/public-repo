package gov.nic.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRootBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gstr9cdata gstr9cdata;
	private Dcupdtls dcupdtls;
	@JsonProperty("fil_dt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date filDt;

	public void setGstr9cdata(Gstr9cdata gstr9cdata) {
		this.gstr9cdata = gstr9cdata;
	}

	public Gstr9cdata getGstr9cdata() {
		return gstr9cdata;
	}

	public void setDcupdtls(Dcupdtls dcupdtls) {
		this.dcupdtls = dcupdtls;
	}

	public Dcupdtls getDcupdtls() {
		return dcupdtls;
	}

	public void setFilDt(Date filDt) {
		this.filDt = filDt;
	}

	public Date getFilDt() {
		return filDt;
	}

}