package gov.nic.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.nic.common.CallG2GApi;
import gov.nic.common.GstUtil;
import gov.nic.model.AuthenticationDetails;
import gov.nic.repository.CommonRepository;

@Service
public class CommonServiceImpl implements CommonService {

	final static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	@Autowired
	private CommonRepository commonDao;

	@Override
	public AuthenticationDetails authToken(String userid, String password) {
		AuthenticationDetails authDetails = null;
		String encoded_app_key = null, auth_token = null, auth_response = null, auth_sek = null, encoded_ek = null;
		String encrypt_password = null, encrypt_session_id = null;
		GstUtil gstUtil = null;
		CallG2GApi callG2GApi = null;
		try {
			logger.info("Enter authToken method():---");
			Calendar cal = Calendar.getInstance();
			String currentTm = sdf.format(cal.getTime());
			gstUtil = new GstUtil();
			callG2GApi = new CallG2GApi();
			authDetails = new AuthenticationDetails();
			encoded_app_key = new GstUtil().generateSecureKey();
			/* Encrypt password and App_Key with Public Key */
			encrypt_password = gstUtil.encryptwithPK_PEM(password.getBytes("UTF-8"));
//			authDetails.setEncrypt_password(encrypt_password);
			encrypt_session_id = gstUtil.encryptwithPK_PEM(GstUtil.decodeBase64StringTOByte(encoded_app_key));
			/* Authentication check with userId and Password */
			auth_response = callG2GApi.authToken(CLIENT_ID, CLIENT_SECRET, userid, encrypt_password, STATE_CD,encrypt_session_id);
			logger.info("auth_response::------" + auth_response);
			if (new JSONObject(auth_response).getString("status_cd").equals("1")) {
				auth_token = new JSONObject(auth_response).getString("auth_token");
				authDetails.setAuth_token(auth_token);
				auth_sek = new JSONObject(auth_response).getString("sek");
				authDetails.setAuth_sek(auth_sek);
				byte[] decrypt_auth_sek = GstUtil.decrypt(auth_sek,(GstUtil.decodeBase64StringTOByte(encoded_app_key)));
				encoded_ek = GstUtil.encodeBase64String(decrypt_auth_sek);
				authDetails.setEncoded_ek(encoded_ek);
				authDetails.setUserId(userid);
				authDetails.setEncoded_app_key(encoded_app_key);
				authDetails.setToken_time(currentTm);
				authDetails.setEncrypt_password(encrypt_password);
				authDetails.setEncrypt_session_id(encrypt_session_id);
				boolean saveAuth_flag=commonDao.saveAuthdetails(authDetails);
				logger.info("Authentication save successfully......:"+saveAuth_flag);
			} else {
				authDetails = null;
			}

		} catch (Exception ex) {
			logger.error("Exception into authToken method():--" + ex.getMessage());
			authDetails = null;
		}
		logger.info("Exit from authToken method():---");
		return authDetails;
	}

	@Override
	public AuthenticationDetails getAuthdetails(String userId, String password) throws Exception {

		AuthenticationDetails authDetails = null;
		long dbtimeinmili = 0;
		try {
			logger.info("Enter into getAuthdetails:--");
			authDetails = commonDao.getAuthdetails(userId, password);
			if (authDetails != null) {
				String tokenTime = authDetails.getToken_time();
				if (tokenTime != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					Date date = sdf.parse(tokenTime);
					dbtimeinmili = date.getTime();
					Timestamp ts = new Timestamp(System.currentTimeMillis());
					long diff = (ts.getTime() - dbtimeinmili) / (1000 * 3600);
					logger.info("Time difference:" + diff);
					if (ts.getTime() - dbtimeinmili > 18000000) {
						logger.info("Token time bigger than 5 hours");
						authDetails = null;
					}
				} else {
					authDetails = null;
				}
			} else {
				authDetails = null;
			}
		} catch (Exception e) {
			logger.error("Exception in getAuthdetails():-" + e.getMessage());
			authDetails = null;
		}
		logger.info("Exit from getAuthdetails:--");
		return authDetails;
	}

}
