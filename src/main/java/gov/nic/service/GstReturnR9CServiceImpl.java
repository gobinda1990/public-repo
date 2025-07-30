package gov.nic.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.nic.common.ArchiveExtractorforGSTFile;
import gov.nic.common.CallG2GApi;
import gov.nic.common.CustomeObjectMapper;
import gov.nic.common.DownloadFile;
import gov.nic.common.GstUtil;
import gov.nic.common.JsonFinder;
import gov.nic.common.ParseDetailsJsonFile;
import gov.nic.model.AuthenticationDetails;
import gov.nic.model.JsonRootBean;
import gov.nic.model.ReturnR9Cnt;
import gov.nic.model.ReturnR9CntDet;
import gov.nic.repository.CommonRepository;
import gov.nic.repository.GstR9CRepository;

@Service
public class GstReturnR9CServiceImpl extends GstReturnR9CService {

	final static Logger logger = Logger.getLogger(GstReturnR9CServiceImpl.class);
	CallG2GApi callG2GApi = null;
	GstUtil gstUtil = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	private CommonRepository commonDao;
	
	@Autowired
	private GstR9CRepository gstR9CRepository;

	@Override
	public void callGstReturnR9C(AuthenticationDetails authDet) throws IOException {
		String response_json_return_cnt = null;
		String response_json_return_file = null;
		String response_return_data = null;
		ReturnR9Cnt retR9Cnt = null;
		ReturnR9CntDet retR9CntDet = null;
		List<ReturnR9CntDet> retR9CntList = null;
		JsonRootBean jsonRtBean = null;
		List<JsonRootBean> jsonRtBnList = null;
		File temp_fold = null;
		int tot_cnt = 0;
		try {
			String startTm = "";
			Date dt = commonDao.getR9CMaxDate();
			String dat = sdf.format(dt);
			logger.info("Last calling date:----" + dat);
			String[] startTmDate = dat.split("-");
			int day = Integer.parseInt(startTmDate[0]);
			int month = Integer.parseInt(startTmDate[1]) - 1;
			int year = Integer.parseInt(startTmDate[2]);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day);
			cal.add(Calendar.DATE, 1);
			startTm = sdf.format(cal.getTime());
			logger.info("API Calling Date:--" + startTm);
			String uri_param = "action=FILECNT&type=R9C&state_cd=19&date=" + startTm + "";
			String url = URI_RETURN_R9_FILECOUNT.concat("?").concat(uri_param);
			logger.info("Enter into callGstReturnR9C :---");
			callG2GApi = new CallG2GApi();
			response_json_return_cnt = callG2GApi.getApi(url, CLIENT_ID, CLIENT_SECRET, authDet.getUserId(), STATE_CD,
					authDet.getAuth_token());
			logger.info("Return R9C count response:--" + response_json_return_cnt);
			boolean return_R9_cnt_flag = GstReturnR9CService.returnR9responseValidate(authDet,
					response_json_return_cnt);
			if (return_R9_cnt_flag == true) {
				response_return_data = new JSONObject(response_json_return_cnt).getString("data");
				String response_data = new String(Base64.decodeBase64(response_return_data));
				logger.info("response_data count:--" + response_data);
				retR9Cnt = CustomeObjectMapper.parseReturnR9Count(response_data);
				// logger.info("Return R9C count:--" + retR9Cnt);
			}
			if (retR9Cnt != null) {
				retR9CntList = new ArrayList<ReturnR9CntDet>();
				int r9cn = retR9Cnt.getNum_files();
				logger.info("Total File count:--" + r9cn);
				if (r9cn > 0) {
					jsonRtBnList = new ArrayList<JsonRootBean>();				
					for (int i = 1; i <= r9cn; i++) {
						String uri_param_dt = "action=FILEDET&state_cd=19&type=R9C&date=" + startTm + "&file_num=" + i;
						String url_file = URI_RETURN_R9_DETAILS.concat("?").concat(uri_param_dt);
						response_json_return_file = callG2GApi.getApi(url_file, CLIENT_ID, CLIENT_SECRET,
								authDet.getUserId(), STATE_CD, authDet.getAuth_token());
						boolean return_R9_flag = GstReturnR9CService.returnR9responseValidate(authDet,
								response_json_return_file);
						if (return_R9_flag == true) {
							String response_return_file_data = new JSONObject(response_json_return_file).getString("data");
							String response_data = new String(Base64.decodeBase64(response_return_file_data));
							retR9CntDet = CustomeObjectMapper.parseReturnR9File(response_data);
							retR9CntList.add(retR9CntDet);
							tot_cnt = tot_cnt + retR9CntDet.getCnt();
							logger.info("Per File JSON COUNT:--" + retR9CntDet.getCnt());
							if (retR9CntDet != null) {
								String downloadUrl = retR9CntDet.getUrl();
								String temp_path = "F:\\ReturnR9CFile";
								String path = null;
								temp_fold = new File(temp_path);
								if (!temp_fold.exists()) {
									temp_fold.mkdir();
								}
								DownloadFile.retryDownload(downloadUrl, temp_path);
								try {
									String outputFile = ArchiveExtractorforGSTFile
											.getFileName(new File(temp_path + "\\a.tar.gz"), temp_path);
									File tarFile = new File(outputFile);
									tarFile = new ArchiveExtractorforGSTFile()
											.deCompressGZipFile(new File(temp_path + "\\a.tar.gz"), tarFile);
									new ArchiveExtractorforGSTFile().TarGZExtractor(tarFile, new File(temp_path));
									JsonFinder jsonFinder = new JsonFinder();
									path = ParseDetailsJsonFile.getFilePath(temp_path, 100);
									File[] jsonFiles = jsonFinder.finder(temp_path + "\\" + path);
									if (jsonFiles.length > 0) {
										for (int j = 0; j < jsonFiles.length; j++) {
											String file = jsonFiles[j].getName();
											jsonRtBean = CustomeObjectMapper.parseReturnR9FileData(
													temp_path.concat("\\" + path + "\\").concat(file));
											jsonRtBnList.add(jsonRtBean);
										}
									}
								} catch (Exception ex) {
									logger.error("Exception into JSON file Parse:--" + ex);
									logger.error("URL IS:--" + downloadUrl);
								} finally {
									ParseDetailsJsonFile.FileDelete(new File(temp_path + "\\" + path));
									ParseDetailsJsonFile.FileDelete(temp_fold);
								}
							}
						} else {
							logger.info("response status:---" + return_R9_flag);
						}
					}

					logger.info("Total JSON count:--" + tot_cnt + "::Parse JSON count::" + jsonRtBnList.size());
					if (tot_cnt == jsonRtBnList.size()) {
						
						String msg = gstR9CRepository.insertR9CData(jsonRtBnList, retR9CntList, retR9Cnt, startTm);
						logger.info("Insert Status:--" + msg);
						if (msg.equals("success")) {
							//map.put("success", "insert done...");
						} else {
						//	map.put("fail", "unsuccessful");
						}
					} else {
						logger.info("Data missnatch:---");
					}
				}

			} else {
				commonDao.R9CAPIDateInsert(startTm);
				logger.info("API Date insert done......");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
