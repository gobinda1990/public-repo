package gov.nic.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import gov.nic.model.AddLiab;
import gov.nic.model.AuditAddr;
import gov.nic.model.AuditedData;
import gov.nic.model.Gstr9cdata;
import gov.nic.model.JsonRootBean;
import gov.nic.model.ReturnR9Cnt;
import gov.nic.model.ReturnR9CntDet;
import gov.nic.model.Rsn;
import gov.nic.model.Table5;
import gov.nic.model.Table6;
import gov.nic.model.Table7;

@Repository
public class GstR9CRepositoryImpl implements GstR9CRepository {

	final static Logger logger = Logger.getLogger(GstR9CRepositoryImpl.class);
	public static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	final Date dt = new Date();
	java.sql.Date sysDt = new java.sql.Date(dt.getTime());
	@Autowired
	public JdbcTemplate jdbctemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public String insertR9CData(List<JsonRootBean> jsonRtBnList, List<ReturnR9CntDet> retR9CntList,
			ReturnR9Cnt retR9Cnt, String dt) throws Exception {

		logger.info("Enter into insertR9CData:---");
		int tab6Cnt = 0;
		int cnt[] = jdbctemplate.batchUpdate(QRY_R9C_ADT_DET, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				JsonRootBean jsonBean = jsonRtBnList.get(i);
				Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
				AuditedData auditdt = gstr9cdata.getAuditedData();
				AddLiab addlib = auditdt.getAddLiab();
				AuditAddr audaddr = addlib.getAuditAddr();
				ps.setString(1, auditdt.getGstin());
				ps.setString(2, auditdt.getFp());
				ps.setDate(3, jsonBean.getFilDt());
				ps.setString(4, auditdt.getLglName());
				ps.setString(5, auditdt.getTrdName());
				ps.setString(6, auditdt.getActName());
				ps.setString(7, auditdt.getIsauditor());
				ps.setString(8, auditdt.getArn());
				ps.setDate(9, auditdt.getArn_dt());
				ps.setString(10, auditdt.getSignatoryname());
				ps.setString(11, auditdt.getDesignation());
				ps.setString(12, addlib.getPlace());
				ps.setString(13, addlib.getSignatory());
				ps.setString(14, addlib.getMemNo());
				ps.setDate(15, addlib.getDate());
				ps.setString(16, addlib.getPanNo());
				ps.setString(17, audaddr != null ? audaddr.getBno() : null);
				ps.setString(18, audaddr != null ? audaddr.getFno() : null);
				ps.setString(19, audaddr != null ? audaddr.getBuilding() : null);
				ps.setString(20, audaddr != null ? audaddr.getRoad() : null);
				ps.setString(21, audaddr != null ? audaddr.getCity() : null);
				ps.setString(22, audaddr != null ? audaddr.getDistrict() : null);
				ps.setString(23, audaddr != null ? audaddr.getState() : null);
				ps.setString(24, audaddr != null ? audaddr.getPinCode() : null);
				ps.setDate(25, sysDt);
			}
			@Override
			public int getBatchSize() {
				return jsonRtBnList.size();
			}
		});
		logger.info("GST_RET_R9C_AUDIT_DET count:-" + cnt.length);

		int r9c_tab5[] = jdbctemplate.batchUpdate(QRY_R9C_ADT_TAB5, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				JsonRootBean jsonBean = jsonRtBnList.get(i);
				Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
				AuditedData auditdt = gstr9cdata.getAuditedData();
				Table5 tab5 = auditdt.getTable5();
				ps.setString(1, auditdt.getGstin());
				ps.setString(2, auditdt.getFp());
				ps.setString(3, auditdt.getArn());
				ps.setLong(4, tab5.getTurnovr());
				ps.setLong(5, tab5.getUnbilRevBeg());
				ps.setLong(6, tab5.getUnadjAdvEnd());
				ps.setLong(7, tab5.getDmdSup());
				ps.setLong(8, tab5.getCrdNtsIssued());
				ps.setLong(9, tab5.getTrdDis());
				ps.setLong(10, tab5.getTurnovrAprJun());
				ps.setLong(11, tab5.getUnbilRevEnd());
				ps.setLong(12, tab5.getUnadjAdvBeg());
				ps.setLong(13, tab5.getCrdNoteAcc());
				ps.setLong(14, tab5.getAdjDta());
				ps.setLong(15, tab5.getTrunovrComp());
				ps.setLong(16, tab5.getAdjTurnDec());
				ps.setLong(17, tab5.getAdjTurnFef());
				ps.setLong(18, tab5.getAdjTurnOthrsn());
				ps.setLong(19, tab5.getAnnulTurnAdj());
				ps.setLong(20, tab5.getAnnulTurnDecl());
				ps.setLong(21, tab5.getUnrecTurnovr());
				ps.setDate(22, sysDt);
			}
			@Override
			public int getBatchSize() {
				return jsonRtBnList.size();
			}
		});
		logger.info("GST_RET_R9C_TAB5 count:-" + r9c_tab5.length);

		for (JsonRootBean jsonBn : jsonRtBnList) {
			Gstr9cdata gstr9cdata = jsonBn.getGstr9cdata();
			AuditedData auditdt = gstr9cdata.getAuditedData();
			Table6 tab6 = auditdt.getTable6();
			if (tab6 != null) {
				List<Rsn> rsnlist = tab6.getRsn();
				if (rsnlist != null) {
					if (rsnlist.size() > 0) {
						int cnt_tab6 = this.insertR9CTab6(rsnlist, auditdt.getGstin(), auditdt.getFp(),
								auditdt.getArn());
						tab6Cnt = tab6Cnt + cnt_tab6;
					}
				}
			}
		}
		logger.info("GST_RET_R9C_TAB6 count:-" + tab6Cnt);
		
		int cnt_tab7[]=jdbctemplate.batchUpdate(QRY_R9C_ADT_TAB7, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				JsonRootBean jsonBean = jsonRtBnList.get(i);
				Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
				AuditedData auditdt = gstr9cdata.getAuditedData();
				Table7 tab7 = auditdt.getTable7();
				ps.setString(1, auditdt.getGstin());
				ps.setString(2, auditdt.getFp());
				ps.setString(3, auditdt.getArn());
				ps.setLong(4, tab7.getAnnulTurnAdj());
				ps.setLong(5, tab7.getOthrTurnovr());
				ps.setLong(6, tab7.getZeroSup());
				ps.setLong(7, tab7.getRevSup());
				ps.setLong(8, tab7.getTaxTurnAdj());
				ps.setLong(9, tab7.getTaxTurnAnnul());
				ps.setLong(10, tab7.getUnrecTaxTurn());
				ps.setDate(11, sysDt);
				
			}			
			@Override
			public int getBatchSize() {
				return jsonRtBnList.size();
			}
		});
		logger.info("GST_RET_R9C_TAB6 count:-" + cnt_tab7.length);

		return "success";
	}

	public int insertR9CTab6(List<Rsn> rsnlist, String gstin, String fp, String arn) {

		int cnt6[] = jdbctemplate.batchUpdate(QRY_R9C_ADT_TAB6, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Rsn rsn = rsnlist.get(i);
				ps.setString(1, gstin);
				ps.setString(2, fp);
				ps.setString(3, arn);
				ps.setString(4, rsn.getNumber());
				ps.setString(5, rsn.getDesc());
				ps.setDate(6, sysDt);

			}
			@Override
			public int getBatchSize() {
				return rsnlist.size();
			}
		});
		return cnt6.length;
	}

}
