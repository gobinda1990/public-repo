package gov.nic.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuditedData {

	private String gstin;
	private String fp;
	@JsonProperty("lgl_name")
	private String lglName;
	@JsonProperty("trd_name")
	private String trdName;
	@JsonProperty("act_name")
	private String actName;
	private String isauditor;
	private String arn;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date arn_dt;
	private String signatoryname;
	private String designation;
	private Table5 table5;
	private Table6 table6;
	private Table7 table7;
	private Table8 table8;
	private Table9 table9;
	private Table10 table10;
	private Table12 table12;
	private Table13 table13;
	private Table14 table14;
	private Table15 table15;
	private Table16 table16;
	@JsonProperty("add_liab")
	private AddLiab addLiab;

	public Table16 getTable16() {
		return table16;
	}

	public void setTable16(Table16 table16) {
		this.table16 = table16;
	}

	public Date getArn_dt() {
		return arn_dt;
	}

	public void setArn_dt(Date arn_dt) {
		this.arn_dt = arn_dt;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getFp() {
		return fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

	public String getLglName() {
		return lglName;
	}

	public void setLglName(String lglName) {
		this.lglName = lglName;
	}

	public String getTrdName() {
		return trdName;
	}

	public void setTrdName(String trdName) {
		this.trdName = trdName;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getIsauditor() {
		return isauditor;
	}

	public void setIsauditor(String isauditor) {
		this.isauditor = isauditor;
	}

	public String getArn() {
		return arn;
	}

	public void setArn(String arn) {
		this.arn = arn;
	}

	public String getSignatoryname() {
		return signatoryname;
	}

	public void setSignatoryname(String signatoryname) {
		this.signatoryname = signatoryname;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Table5 getTable5() {
		return table5;
	}

	public void setTable5(Table5 table5) {
		this.table5 = table5;
	}

	public Table6 getTable6() {
		return table6;
	}

	public void setTable6(Table6 table6) {
		this.table6 = table6;
	}

	public Table7 getTable7() {
		return table7;
	}

	public void setTable7(Table7 table7) {
		this.table7 = table7;
	}

	public Table8 getTable8() {
		return table8;
	}

	public void setTable8(Table8 table8) {
		this.table8 = table8;
	}

	public Table9 getTable9() {
		return table9;
	}

	public void setTable9(Table9 table9) {
		this.table9 = table9;
	}

	public Table10 getTable10() {
		return table10;
	}

	public void setTable10(Table10 table10) {
		this.table10 = table10;
	}

	public Table12 getTable12() {
		return table12;
	}

	public void setTable12(Table12 table12) {
		this.table12 = table12;
	}

	public Table13 getTable13() {
		return table13;
	}

	public void setTable13(Table13 table13) {
		this.table13 = table13;
	}

	public Table14 getTable14() {
		return table14;
	}

	public void setTable14(Table14 table14) {
		this.table14 = table14;
	}

	public Table15 getTable15() {
		return table15;
	}

	public void setTable15(Table15 table15) {
		this.table15 = table15;
	}

	public AddLiab getAddLiab() {
		return addLiab;
	}

	public void setAddLiab(AddLiab addLiab) {
		this.addLiab = addLiab;
	}

	@Override
	public String toString() {
		return "AuditedData [gstin=" + gstin + ", fp=" + fp + ", lglName=" + lglName + ", trdName=" + trdName
				+ ", actName=" + actName + ", isauditor=" + isauditor + ", arn=" + arn + ", signatoryname="
				+ signatoryname + ", designation=" + designation + "]";
	}

}