package gov.nic.service;

import gov.nic.model.AuthenticationDetails;

public interface CommonService {

	public static final String CLIENT_ID = "l7xx07c7cdc2113543d595a1e1bdcb7244f9";

	public static final String CLIENT_SECRET = "55bb916b879e45ed88f3ef0bfcf80314";

	public static final String STATE_CD = "19";

	public  AuthenticationDetails getAuthdetails(String userId, String password) throws Exception;

	public  AuthenticationDetails authToken(String user_name, String password);

}
