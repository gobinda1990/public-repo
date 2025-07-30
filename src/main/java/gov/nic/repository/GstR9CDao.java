package gov.nic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import gov.nic.connection.ReturnConnection;
import gov.nic.model.JsonRootBean;
import gov.nic.model.ReturnR9Cnt;
import gov.nic.model.ReturnR9CntDet;

public abstract class GstR9CDao {

	final static Logger logger = Logger.getLogger(GstR9CDao.class);
	
	public static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	
	public static final String QRY_R9_API_DT = "INSERT INTO GST_RET_R9C_API_DT(API_DT) VALUES(?)";

	public static final String QRY_R9_CNT = "INSERT INTO GST_RET_R9C_CNT(EOD_CLOSED,LOG_DT,FILE_NUM) VALUES(?,?,?)";

	public static final String QRY_R9_CNT_DTL = "INSERT INTO GST_RET_R9C_CNT_DTLS(CNT,FILE_NUM,FILE_HASH,URL,LOG_DT) VALUES(?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_DET="INSERT INTO GST_RET_R9C_AUDIT_DET(GSTIN,RET_PRD,FILL_DT,LEGAL_NM,TRADE_NM,ACT_NM,IS_AUDITOR,"
			+ "ARN,ARN_DT,SIGNATORY_NM,DESIGNATION,ADD_LIAB_PLACE,SIGNATORY,MEM_NO,DT,PAN_NO,B_NO,F_NO,B_NM,ST_NM,CITY,DIST,STATE,PIN_CD,API_DT) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB5="INSERT INTO GST_RET_R9C_TAB5(GSTIN,RET_PRD,ARN,TURN_OVR,UNBIL_REV_BEG,UNADJ_ADV_END,DMD_SUP,"
			+ "CRD_NTS_ISSUED,TRD_DIS,TURNOVR_APR_JUN,UNBIL_REV_END,UNADJ_ADV_BEG,CRD_NOTE_ACC,ADJ_DTA,TURNOVR_COMP,ADJ_TURN_SEC," 
			+ "ADJ_TURN_FEF,ADJ_TURN_OTHRSN,ANNUL_TURN_ADJ,ANNUL_TURN_DECL,UNREC_TURNOVR)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB6="INSERT INTO GST_RET_R9C_TAB6(GSTIN,RET_PRD,ARN,NUM_CD,DESC_DET)VALUES(?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB7="INSERT INTO GST_RET_R9C_TAB7(GSTIN,RET_PRD,ARN,ANNUL_TURN_ADJ,OTHR_TURNOVR,ZERO_SUP,"
			+ "REV_SUP,TAX_TURN_ADJ,TAX_TURN_ANNUL,UNREC_TAX_TURN)VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB8="INSERT INTO GST_RET_R9C_TAB8(GSTIN,RET_PRD,ARN,NUM_CD,DESC_DET)VALUES(?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB9_RT="INSERT INTO GST_RET_R9C_TAB9_RT(GSTIN,RET_PRD,ARN,RT,TAX_VAL,CGST,SGST,IGST,CESS)VALUES(?,?,?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB9_PBL="INSERT INTO GST_RET_R9C_TAB9_TOT_AMT_PBL(GSTIN,RET_PRD,ARN,CGST,SGST,IGST,CESS)VALUES(?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB9_PAID="INSERT INTO GST_RET_R9C_TAB9_TOT_AMT_PAID(GSTIN,RET_PRD,ARN,CGST,SGST,IGST,CESS)VALUES(?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB9_UNREC="INSERT INTO GST_RET_R9C_TAB9_UNREC_AMT(GSTIN,RET_PRD,ARN,CGST,SGST,IGST,CESS)VALUES(?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB10="INSERT INTO GST_RET_R9C_TAB10(GSTIN,RET_PRD,ARN,NUM_CD,DESC_DET) VALUES(?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB12="INSERT INTO GST_RET_R9C_TAB12(GSTIN,RET_PRD,ARN,ITC_AVAIL,ITC_BOOK_EARL,ITC_BOOK_CURR,"
			+ "ITC_AVAIL_AUDITED,ITC_CLAIM,UNREC_ITC)VALUES(?,?,?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB13="INSERT INTO GST_RET_R9C_TAB13(GSTIN,RET_PRD,ARN,NUM_CD,DESC_DET)VALUES(?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB14="INSERT INTO GST_RET_R9C_TAB14(GSTIN,RET_PRD,ARN,TOT_ELIG_ITC,ITC_CLAIM,UNREC_ITC)VALUES(?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB14_ITM="INSERT INTO GST_RET_R9C_TAB14_ITM_DET(GSTIN,RET_PRD,ARN,DESC_DET,VAL,ITC_AMT,ITC_AVAIL)"
			+ "VALUES(?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB15="INSERT INTO GST_RET_R9C_TAB15(GSTIN,RET_PRD,ARN,NUM_CD,DESC_DET) VALUES(?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_TAB16="INSERT INTO GST_RET_R9C_TAB16(GSTIN,RET_PRD,ARN,CGST,SGST,IGST,CESS,"
			+ "INTEREST,PENALTY)VALUES(?,?,?,?,?,?,?,?,?)";	
	
	public static final String QRY_R9C_CERT_DET="INSERT INTO GST_RET_R9C_CERT_DET(GSTIN,RET_PRD,ARN,PRO_NOUN,BAL_SHEET_DATE,ACC_TYP,"
			+ "BEG_DATE,END_DATE,CASH_FROM_DATE,CASH_TO_DATE,TAXPAYER_NAME,B_NO,F_NO,B_NM,ST_NM,CITY,DIST,STATE,PIN_CD,DOC_STAT,PLACE,"
			+ "SIGNATORY,MEM_NO,DT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_ADT_ADD="INSERT INTO GST_RET_R9C_CERT_AUDIT_ADDR(GSTIN,RET_PRD,ARN,B_NO,F_NO,B_NM,ST_NM,CITY,DIST,"
			+ "STATE,PIN_CD) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String QRY_R9C_CERT_TXT_PART1="INSERT INTO GST_RET_R9C_CERT_TXT_PB1(GSTIN,RET_PRD,ARN,IS_HAVE,IS_AGREE,INFO_STAT,B_NO,"
			+ "F_NO,B_NM,ST_NM,CITY,DIST,STATE,PIN_CD) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public abstract boolean insertR9CData(List<JsonRootBean> jsonRtBnList, List<ReturnR9CntDet> retR9CntList,
			ReturnR9Cnt retR9Cnt, String dt) throws Exception;

	public static boolean insertR9CntDet(List<ReturnR9CntDet> retR9CntList, ReturnR9Cnt retR9Cnt, Connection con,
			String dt) throws ParseException {
		boolean flag = false;
		int[] r9_cnt_dt;
		int cnt = 0;
		Date dat = sdf1.parse(dt);
		java.sql.Date sqlStartDate = new java.sql.Date(dat.getTime());
		try {
			logger.info("Enter into insertR9CntDet:---");
			if (con != null) {
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9_CNT_DTL)) {
					for (int i = 0; i < retR9CntList.size(); i++) {
						ReturnR9CntDet retR9cntDt = retR9CntList.get(i);
						stmt.setInt(1, retR9cntDt.getCnt());
						stmt.setInt(2, retR9cntDt.getFile_num());
						stmt.setString(3, retR9cntDt.getHash());
						stmt.setString(4, retR9cntDt.getUrl());
						stmt.setDate(5, sqlStartDate);
						stmt.addBatch();
					}
					r9_cnt_dt = stmt.executeBatch();
					logger.info("GST_RET_R9C_CNT_DTLS:--" + r9_cnt_dt.length);
				}
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9_CNT)) {
					stmt.setString(1, retR9Cnt.getEod_closed());
					stmt.setDate(2, retR9Cnt.getDate());
					stmt.setInt(3, retR9Cnt.getNum_files());
					cnt = stmt.executeUpdate();
					logger.info("GST_RET_R9C_CNT:--" + cnt);
				}
				if (cnt > 0) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				logger.info("connection is null:---" + con);
				flag = false;
			}
		} catch (Exception ex) {
			logger.error("Exception into insertR9CntDet:--" + ex.getMessage());
			flag = false;
		}
		return flag;
	}

	public static boolean insertR9ApiDt(String dt, Connection con) throws ParseException {
		boolean flag = false;
		Date dat = sdf1.parse(dt);
		java.sql.Date sqlStartDate = new java.sql.Date(dat.getTime());
		int cnt = 0;
		try {
			logger.info("Enter into insertR9ApiDt:---");
			if (con != null) {
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9_API_DT)) {
					stmt.setDate(1, sqlStartDate);
					cnt = stmt.executeUpdate();
					logger.info("GST_RET_R9C_API_DT:--" + cnt);
				}
				if (cnt > 0) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} catch (Exception ex) {
			logger.error("Exception into insertR9CntDet:--" + ex.getMessage());
			flag = false;
		}
		return flag;
	}

	public static java.sql.Date getApiDt() throws SQLException {
		java.sql.Date dt = null;
		Connection con = null;
		ResultSet rs = null;
		String qry_max_dt = "select max(API_DT) FROM GST_RET_R9C_API_DT";
		try {
			con = new ReturnConnection().getConnection();
			if (con != null) {
				try (PreparedStatement stmt = con.prepareStatement(qry_max_dt)) {
					rs = stmt.executeQuery();
					if (rs.next()) {
						dt = rs.getDate(1);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Exception into getCurrentR1Dt:-" + ex.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return dt;
	}

}
