package gov.nic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import gov.nic.connection.ReturnConnection;
import gov.nic.model.AddLiab;
import gov.nic.model.AuditAddr;
import gov.nic.model.AuditedData;
import gov.nic.model.CertData;
import gov.nic.model.Certificate;
import gov.nic.model.Gstr9cdata;
import gov.nic.model.Items;
import gov.nic.model.JsonRootBean;
import gov.nic.model.Rate;
import gov.nic.model.ReturnR9Cnt;
import gov.nic.model.ReturnR9CntDet;
import gov.nic.model.Rsn;
import gov.nic.model.Table10;
import gov.nic.model.Table12;
import gov.nic.model.Table13;
import gov.nic.model.Table14;
import gov.nic.model.Table15;
import gov.nic.model.Table16;
import gov.nic.model.Table5;
import gov.nic.model.Table6;
import gov.nic.model.Table7;
import gov.nic.model.Table8;
import gov.nic.model.Table9;
import gov.nic.model.TotAmtPaid;
import gov.nic.model.TotAmtPayable;
import gov.nic.model.UnrecAmt;

public class GstR9cDaoImpl extends GstR9CDao {

	@Override
	public boolean insertR9CData(List<JsonRootBean> jsonRtBnList, List<ReturnR9CntDet> retR9CntList,
			ReturnR9Cnt retR9Cnt, String dt) throws Exception {
		boolean flag = false;
		Connection con = null;
		int[] r9c_adt_dt, r9c_tab5, r9c_tab6, r9c_tab7, r9c_tab8, r9c_tab9_rt, r9c_tab9_plb, r9c_tab9_paid,
				r9c_tab9_unrec, r9c_tab10, r9c_tab12, r9c_tab13, r9c_tab14, r9c_tab14_itm, r9c_tab15, r9c_tab16,
				r9c_cert_det, r9c_cert_adt_add;
		try {
			logger.info("Enter into insertR9CData:--");
			con = new ReturnConnection().getConnection();
			con.setAutoCommit(false);
			if (con != null) {

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_DET)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setDate(3, jsonBean.getFilDt());
						stmt.setString(4, auditdt.getLglName());
						stmt.setString(5, auditdt.getTrdName());
						stmt.setString(6, auditdt.getActName());
						stmt.setString(7, auditdt.getIsauditor());
						stmt.setString(8, auditdt.getArn());
						stmt.setDate(9, auditdt.getArn_dt());
						stmt.setString(10, auditdt.getSignatoryname());
						stmt.setString(11, auditdt.getDesignation());

						AddLiab addlib = auditdt.getAddLiab();
						stmt.setString(12, addlib.getPlace());
						stmt.setString(13, addlib.getSignatory());
						stmt.setString(14, addlib.getMemNo());
						stmt.setDate(15, addlib.getDate());
						stmt.setString(16, addlib.getPanNo());
						AuditAddr audaddr = addlib.getAuditAddr();
						if (audaddr != null) {
							stmt.setString(17, audaddr.getBno());
							stmt.setString(18, audaddr.getFno());
							stmt.setString(19, audaddr.getBuilding());
							stmt.setString(20, audaddr.getRoad());
							stmt.setString(21, audaddr.getCity());
							stmt.setString(22, audaddr.getDistrict());
							stmt.setString(23, audaddr.getState());
							stmt.setString(24, audaddr.getPinCode());
						} else {
							stmt.setString(17, null);
							stmt.setString(18, null);
							stmt.setString(19, null);
							stmt.setString(20, null);
							stmt.setString(21, null);
							stmt.setString(22, null);
							stmt.setString(23, null);
							stmt.setString(24, null);
						}
						Date dat = sdf1.parse(dt);
						java.sql.Date sqlStartDate = new java.sql.Date(dat.getTime());
						stmt.setDate(25, sqlStartDate);
						stmt.addBatch();
					}
					r9c_adt_dt = stmt.executeBatch();
					logger.info("GST_RET_R9C_AUDIT_DET:--" + r9c_adt_dt.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB5)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setString(3, auditdt.getArn());
						Table5 tab5 = auditdt.getTable5();
						stmt.setLong(4, tab5.getTurnovr());
						stmt.setLong(5, tab5.getUnbilRevBeg());
						stmt.setLong(6, tab5.getUnadjAdvEnd());
						stmt.setLong(7, tab5.getDmdSup());
						stmt.setLong(8, tab5.getCrdNtsIssued());
						stmt.setLong(9, tab5.getTrdDis());
						stmt.setLong(10, tab5.getTurnovrAprJun());
						stmt.setLong(11, tab5.getUnbilRevEnd());
						stmt.setLong(12, tab5.getUnadjAdvBeg());
						stmt.setLong(13, tab5.getCrdNoteAcc());
						stmt.setLong(14, tab5.getAdjDta());
						stmt.setLong(15, tab5.getTrunovrComp());
						stmt.setLong(16, tab5.getAdjTurnDec());
						stmt.setLong(17, tab5.getAdjTurnFef());
						stmt.setLong(18, tab5.getAdjTurnOthrsn());
						stmt.setLong(19, tab5.getAnnulTurnAdj());
						stmt.setLong(20, tab5.getAnnulTurnDecl());
						stmt.setLong(21, tab5.getUnrecTurnovr());
						stmt.addBatch();
					}
					r9c_tab5 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB5:--" + r9c_tab5.length);
				}
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB6)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table6 tab6 = auditdt.getTable6();
						if (tab6 != null) {
							List<Rsn> rsn = tab6.getRsn();
							if (rsn != null) {
								for (int j = 0; j < rsn.size(); j++) {
									stmt.setString(1, auditdt.getGstin());
									stmt.setString(2, auditdt.getFp());
									stmt.setString(3, auditdt.getArn());
									stmt.setString(4, rsn.get(j).getNumber());
									stmt.setString(5, rsn.get(j).getDesc());
									stmt.addBatch();
								}
							}
						}
					}
					r9c_tab6 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB6:--" + r9c_tab6.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB7)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table7 tab7 = auditdt.getTable7();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setString(3, auditdt.getArn());
						stmt.setLong(4, tab7.getAnnulTurnAdj());
						stmt.setLong(5, tab7.getOthrTurnovr());
						stmt.setLong(6, tab7.getZeroSup());
						stmt.setLong(7, tab7.getRevSup());
						stmt.setLong(8, tab7.getTaxTurnAdj());
						stmt.setLong(9, tab7.getTaxTurnAnnul());
						stmt.setLong(10, tab7.getUnrecTaxTurn());
						stmt.addBatch();
					}
					r9c_tab7 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB7:--" + r9c_tab7.length);

				}
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB8)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table8 tab8 = auditdt.getTable8();
						if (tab8 != null) {
							List<Rsn> rsn = tab8.getRsn();
							if (rsn != null) {
								for (int j = 0; j < rsn.size(); j++) {
									stmt.setString(1, auditdt.getGstin());
									stmt.setString(2, auditdt.getFp());
									stmt.setString(3, auditdt.getArn());
									stmt.setString(4, rsn.get(j).getNumber());
									stmt.setString(5, rsn.get(j).getDesc());
									stmt.addBatch();
								}
							}
						}
					}
					r9c_tab8 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB8:--" + r9c_tab8.length);
				}
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB9_RT)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table9 tab9 = auditdt.getTable9();
						if (tab9 != null) {
							List<Rate> rate = tab9.getRate();
							if (rate != null) {
								for (int j = 0; j < rate.size(); j++) {
									stmt.setString(1, auditdt.getGstin());
									stmt.setString(2, auditdt.getFp());
									stmt.setString(3, auditdt.getArn());
									stmt.setString(4, rate.get(j).getDesc());
									stmt.setLong(5, rate.get(j).getTaxVal());
									stmt.setLong(6, rate.get(j).getCgst());
									stmt.setLong(7, rate.get(j).getSgst());
									stmt.setLong(8, rate.get(j).getIgst());
									stmt.setLong(9, rate.get(j).getCess());
									stmt.addBatch();
								}
							}
						}
					}
					r9c_tab9_rt = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB9_RT:--" + r9c_tab9_rt.length);
				}
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB9_PBL)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table9 tab9 = auditdt.getTable9();
						TotAmtPayable totamtPay = tab9.getTotAmtPayable();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setString(3, auditdt.getArn());
						stmt.setLong(4, totamtPay.getCgst());
						stmt.setLong(5, totamtPay.getSgst());
						stmt.setLong(6, totamtPay.getIgst());
						stmt.setLong(7, totamtPay.getCess());
						stmt.addBatch();
					}
					r9c_tab9_plb = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB9_TOT_AMT_PBL:--" + r9c_tab9_plb.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB9_PAID)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table9 tab9 = auditdt.getTable9();
						TotAmtPaid totamtPaid = tab9.getTotAmtPaid();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setString(3, auditdt.getArn());
						stmt.setLong(4, totamtPaid.getCgst());
						stmt.setLong(5, totamtPaid.getSgst());
						stmt.setLong(6, totamtPaid.getIgst());
						stmt.setLong(7, totamtPaid.getCess());
						stmt.addBatch();
					}
					r9c_tab9_paid = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB9_TOT_AMT_PAID:--" + r9c_tab9_paid.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB9_UNREC)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table9 tab9 = auditdt.getTable9();
						UnrecAmt unrecAmt = tab9.getUnrecAmt();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setString(3, auditdt.getArn());
						stmt.setLong(4, unrecAmt.getCgst());
						stmt.setLong(5, unrecAmt.getSgst());
						stmt.setLong(6, unrecAmt.getIgst());
						stmt.setLong(7, unrecAmt.getCess());
						stmt.addBatch();
					}
					r9c_tab9_unrec = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB9_UNREC_AMT:--" + r9c_tab9_unrec.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB10)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table10 tab10 = auditdt.getTable10();
						if (tab10 != null) {
							List<Rsn> rsn = tab10.getRsn();
							if (rsn != null) {
								for (int j = 0; j < rsn.size(); j++) {
									stmt.setString(1, auditdt.getGstin());
									stmt.setString(2, auditdt.getFp());
									stmt.setString(3, auditdt.getArn());
									stmt.setString(4, rsn.get(j).getNumber());
									stmt.setString(5, rsn.get(j).getDesc());
									stmt.addBatch();
								}
							}
						}
					}
					r9c_tab10 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB10:--" + r9c_tab10.length);
				}
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB12)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table12 tab12 = auditdt.getTable12();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setString(3, auditdt.getArn());
						stmt.setLong(4, tab12.getItcAvail());
						stmt.setLong(5, tab12.getItcBookEarl());
						stmt.setLong(6, tab12.getItcBookCurr());
						stmt.setLong(7, tab12.getItcAvailAudited());
						stmt.setLong(8, tab12.getItcClaim());
						stmt.setLong(9, tab12.getUnrecItc());
						stmt.addBatch();
					}
					r9c_tab12 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB12:--" + r9c_tab12.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB13)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table13 tab13 = auditdt.getTable13();
						if (tab13 != null) {
							List<Rsn> rsn = tab13.getRsn();
							if (rsn != null) {
								for (int j = 0; j < rsn.size(); j++) {
									stmt.setString(1, auditdt.getGstin());
									stmt.setString(2, auditdt.getFp());
									stmt.setString(3, auditdt.getArn());
									stmt.setString(4, rsn.get(j).getNumber());
									stmt.setString(5, rsn.get(j).getDesc());
									stmt.addBatch();
								}
							}
						}
					}
					r9c_tab13 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB13:--" + r9c_tab13.length);
				}
				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB14)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table14 tab14 = auditdt.getTable14();
						stmt.setString(1, auditdt.getGstin());
						stmt.setString(2, auditdt.getFp());
						stmt.setString(3, auditdt.getArn());
						stmt.setLong(4, tab14.getTotEligItc().getItcAvail());
						stmt.setLong(5, tab14.getItcClaim().getItcAvail());
						stmt.setLong(6, tab14.getUnrecItc().getItcAvail());
						stmt.addBatch();
					}
					r9c_tab14 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB14:--" + r9c_tab14.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB14_ITM)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table14 tab14 = auditdt.getTable14();
						if (tab14 != null) {
							List<Items> items = tab14.getItems();
							if (items != null) {
								for (int j = 0; j < items.size(); j++) {
									stmt.setString(1, auditdt.getGstin());
									stmt.setString(2, auditdt.getFp());
									stmt.setString(3, auditdt.getArn());
									stmt.setString(4, items.get(j).getDesc());
									stmt.setLong(5, items.get(j).getVal());
									stmt.setLong(6, items.get(j).getItcAmt());
									stmt.setLong(7, items.get(j).getItcAvail());
									stmt.addBatch();
								}
							}
						}
					}
					r9c_tab14_itm = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB14_ITM_DET:--" + r9c_tab14_itm.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB15)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table15 tab15 = auditdt.getTable15();
						if (tab15 != null) {
							List<Rsn> rsn = tab15.getRsn();
							if (rsn != null) {
								for (int j = 0; j < rsn.size(); j++) {
									stmt.setString(1, auditdt.getGstin());
									stmt.setString(2, auditdt.getFp());
									stmt.setString(3, auditdt.getArn());
									stmt.setString(4, rsn.get(j).getNumber());
									stmt.setString(5, rsn.get(j).getDesc());
									stmt.addBatch();
								}
							}
						}
					}
					r9c_tab15 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB15:--" + r9c_tab15.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_TAB16)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						AuditedData auditdt = gstr9cdata.getAuditedData();
						Table16 tab16 = auditdt.getTable16();
						if (tab16 != null) {
							stmt.setString(1, auditdt.getGstin());
							stmt.setString(2, auditdt.getFp());
							stmt.setString(3, auditdt.getArn());
							stmt.setLong(4, tab16.getCgst());
							stmt.setLong(5, tab16.getSgst());
							stmt.setLong(6, tab16.getIgst());
							stmt.setLong(7, tab16.getCess());
							stmt.setLong(8, tab16.getInter());
							stmt.setLong(9, tab16.getPen());
							stmt.addBatch();
						}
					}
					r9c_tab16 = stmt.executeBatch();
					logger.info("GST_RET_R9C_TAB16:--" + r9c_tab16.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_CERT_DET)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						Certificate cert = gstr9cdata.getCertificate();
						if (cert != null) {
							CertData certData = cert.getCertData();
							if (certData != null) {
								stmt.setString(1, gstr9cdata.getAuditedData().getGstin());
								stmt.setString(2, gstr9cdata.getAuditedData().getFp());
								stmt.setString(3, gstr9cdata.getAuditedData().getArn());
								stmt.setString(4, certData.getPronoun());
								stmt.setDate(5, certData.getBalSheetDate());
								stmt.setString(6, certData.getAccTyp());
								stmt.setDate(7, certData.getBegDate());
								stmt.setDate(8, certData.getEndDate());
								stmt.setDate(9, null);
								stmt.setDate(10, null);
								stmt.setString(11, certData.getTaxpayerName());
								stmt.setString(12, certData.getAddr().getBno());
								stmt.setString(13, certData.getAddr().getFno());
								stmt.setString(14, certData.getAddr().getBuilding());
								stmt.setString(15, certData.getAddr().getRoad());
								stmt.setString(16, certData.getAddr().getCity());
								stmt.setString(17, certData.getAddr().getDistrict());
								stmt.setString(18, certData.getAddr().getState());
								stmt.setString(19, certData.getAddr().getPinCode());
								stmt.setString(20, certData.getDocStat());
								stmt.setString(21, certData.getPlace());
								stmt.setString(22, certData.getSignatory());
								stmt.setString(23, certData.getMemNo());
								stmt.setDate(24, certData.getDate());
								stmt.addBatch();
							}
						}
					}
					r9c_cert_det = stmt.executeBatch();
					logger.info("GST_RET_R9C_CERT_DET:--" + r9c_cert_det.length);
				}

				try (PreparedStatement stmt = con.prepareStatement(QRY_R9C_ADT_ADD)) {
					for (int i = 0; i < jsonRtBnList.size(); i++) {
						JsonRootBean jsonBean = jsonRtBnList.get(i);
						Gstr9cdata gstr9cdata = jsonBean.getGstr9cdata();
						Certificate cert = gstr9cdata.getCertificate();
						if(cert!=null) {
						CertData certData = cert.getCertData();
						if (certData != null) {
							stmt.setString(1, gstr9cdata.getAuditedData().getGstin());
							stmt.setString(2, gstr9cdata.getAuditedData().getFp());
							stmt.setString(3, gstr9cdata.getAuditedData().getArn());
							stmt.setString(4, certData.getAuditAddr().getBno());
							stmt.setString(5, certData.getAuditAddr().getFno());
							stmt.setString(6, certData.getAuditAddr().getBuilding());
							stmt.setString(7, certData.getAuditAddr().getRoad());
							stmt.setString(8, certData.getAuditAddr().getCity());
							stmt.setString(9, certData.getAuditAddr().getDistrict());
							stmt.setString(10, certData.getAuditAddr().getState());
							stmt.setString(11, certData.getAuditAddr().getPinCode());
							stmt.addBatch();
						}
					}
					}
					r9c_cert_adt_add = stmt.executeBatch();
					logger.info("GST_RET_R9C_CERT_DET:--" + r9c_cert_adt_add.length);
				}

				boolean flag_cnt = GstR9CDao.insertR9CntDet(retR9CntList, retR9Cnt, con, dt);
				boolean flag_api = GstR9CDao.insertR9ApiDt(dt, con);
				if (flag_cnt == true && flag_api == true) {
					con.commit();
					logger.info("Data Successfully insert:----");
					flag = true;
				} else {
					con.rollback();
					logger.info("Data insert failed:----");
					flag = false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception into insertR9CData():-" + ex.getMessage());
			con.rollback();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return flag;
	}

}
