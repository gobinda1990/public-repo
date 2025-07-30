package gov.nic.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CertData {

    private String pronoun;
    
    @JsonProperty("bal_sheet_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date balSheetDate;
    
    @JsonProperty("acc_typ")
    private String accTyp;
    
    @JsonProperty("beg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date begDate;
    
    @JsonProperty("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date endDate;
    
//    @JsonProperty("cash_from_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD/MM/YYYY")
//    private Date cashFromDate;
    
//    @JsonProperty("cash_to_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
//    private Date cashToDate;
    
    @JsonProperty("taxpayer_name")
    private String taxpayerName;
    
    private Addr addr;
    
    @JsonProperty("doc_stat")
    private String docStat;
    
    private String place;
    
    private String signatory;
    
    @JsonProperty("mem_no")
    private String memNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    
    @JsonProperty("audit_addr")
    private AuditAddr auditAddr;
    
    @JsonProperty("cert_textpartb1")
    private CertTextPartb1 certTextpartb1; 
    
    public void setPronoun(String pronoun) {
         this.pronoun = pronoun;
     }
     public String getPronoun() {
         return pronoun;
     }

    public void setBalSheetDate(Date balSheetDate) {
         this.balSheetDate = balSheetDate;
     }
     public Date getBalSheetDate() {
         return balSheetDate;
     }

    public void setAccTyp(String accTyp) {
         this.accTyp = accTyp;
     }
     public String getAccTyp() {
         return accTyp;
     }

    public void setBegDate(Date begDate) {
         this.begDate = begDate;
     }
     public Date getBegDate() {
         return begDate;
     }

    public void setEndDate(Date endDate) {
         this.endDate = endDate;
     }
     public Date getEndDate() {
         return endDate;
     }

//    public void setCashFromDate(Date cashFromDate) {
//         this.cashFromDate = cashFromDate;
//     }
//     public Date getCashFromDate() {
//         return cashFromDate;
//     }

//    public void setCashToDate(Date cashToDate) {
//         this.cashToDate = cashToDate;
//     }
//     public Date getCashToDate() {
//         return cashToDate;
//     }

    public void setTaxpayerName(String taxpayerName) {
         this.taxpayerName = taxpayerName;
     }
     public String getTaxpayerName() {
         return taxpayerName;
     }

    public void setAddr(Addr addr) {
         this.addr = addr;
     }
     public Addr getAddr() {
         return addr;
     }

    public void setDocStat(String docStat) {
         this.docStat = docStat;
     }
     public String getDocStat() {
         return docStat;
     }

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

    public void setCertTextpartb1(CertTextPartb1 certTextpartb1) {
         this.certTextpartb1 = certTextpartb1;
     }
     public CertTextPartb1 getCertTextpartb1() {
         return certTextpartb1;
     }

}