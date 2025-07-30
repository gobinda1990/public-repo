package gov.nic.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import gov.nic.model.AuthenticationDetails;

public class AuthRowMapper implements RowMapper<AuthenticationDetails> {
	
	 @Override
	    public AuthenticationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		 
		  AuthenticationDetails authDetails = new AuthenticationDetails();
		    authDetails.setToken_time(rs.getString("token_time"));
			authDetails.setEncoded_app_key(rs.getString("encoded_app_key"));
			authDetails.setEncrypt_password(rs.getString("encrypt_password"));
			authDetails.setEncrypt_session_id(rs.getString("encrypt_session_id"));
			authDetails.setAuth_token(rs.getString("auth_token"));
			authDetails.setAuth_sek(rs.getString("auth_sek"));
			authDetails.setEncoded_ek(rs.getString("encoded_ek"));	
			authDetails.setUserId(rs.getString("userId"));
			authDetails.setPassword(rs.getString("password"));
	        return authDetails;

	    }

}
