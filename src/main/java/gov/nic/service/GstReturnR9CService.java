package gov.nic.service;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import gov.nic.common.GstUtil;
import gov.nic.model.AuthenticationDetails;

public abstract class GstReturnR9CService {

	final static Logger logger = Logger.getLogger(GstReturnR9CService.class);

	public static final String CLIENT_ID = "l7xx07c7cdc2113543d595a1e1bdcb7244f9";
	public static final String CLIENT_SECRET = "55bb916b879e45ed88f3ef0bfcf80314";
	public static final String STATE_CD = "19";
	public static final String URI_RETURN_R9_FILECOUNT = "https://boapi.internal.gst.gov.in/govtapi/v0.3/returns";
	public static final String URI_RETURN_R9_DETAILS = "https://boapi.internal.gst.gov.in/govtapi/v0.3/returns";

	public abstract void callGstReturnR9C(AuthenticationDetails authDet) throws IOException;

	public static boolean returnR9responseValidate(AuthenticationDetails authDet, String json_response) {
		boolean flag = false;
		try {
			if (new JSONObject(json_response).getString("status_cd").equals("1")) {
				String received_data = new JSONObject(json_response).getString("data");
				String REK = new JSONObject(json_response).getString("rek");
				String received_hmac = new JSONObject(json_response).getString("hmac");
				byte[] DECRYPTED_REK = GstUtil.decrypt(REK, GstUtil.decodeBase64StringTOByte(authDet.getEncoded_ek()));
				/* HMAC Validation */
				String generated_hmac = GstUtil.generateHmac(received_data, DECRYPTED_REK);
				// logger.info("Generated HMAC = " + generated_hmac);
				if (generated_hmac.equalsIgnoreCase(received_hmac)) {
					flag = true;
				}
			}
		} catch (Exception ex) {
			logger.error("Exception into alertresponseValidate:-" + ex.getMessage());
			flag = false;
		}
		return flag;
	}
}
