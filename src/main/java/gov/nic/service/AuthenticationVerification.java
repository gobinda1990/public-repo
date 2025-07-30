package gov.nic.service;

import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.nic.model.AuthenticationDetails;

@Service
public class AuthenticationVerification {

	final static Logger logger = Logger.getLogger(AuthenticationVerification.class);
	private static final String USER_ID = "GSTG2G19";
	private static final String PASSWORD = "Gstn@22012025";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm");

	@Autowired
	private GstReturnR9CService gstRetR9cService;

	@Autowired
	private CommonService commonService;

	public synchronized void gstDataConsume() {

		AuthenticationDetails authDetails = null;
		try {
			logger.info("Enter into gstDataConsume method():- ");
			authDetails = commonService.getAuthdetails(USER_ID, PASSWORD);
			logger.info("Authentication retrive from DB:::" + authDetails);
			if (authDetails == null) {
				authDetails = commonService.authToken(USER_ID, PASSWORD);
				if (authDetails.getUserId() == null) {
					logger.info("API authentication fails..");
				} else {
					logger.info("API authentication success..");
					logger.info("Start Call DRC-03 API:--" + authDetails.getUserId());
					gstRetR9cService.callGstReturnR9C(authDetails);
				}
			} else {
				logger.info("Start Call DRC-03 API:--" + authDetails.getUserId());
				gstRetR9cService.callGstReturnR9C(authDetails);
			}

		} catch (Exception ex) {
			logger.error("Exception into gstDataConsume method():--" + ex.getMessage());
		}
		logger.info("Exit From gstDataConsume method():- ");
	}

}
