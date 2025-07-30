package gov.nic.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import gov.nic.common.AuthRowMapper;
import gov.nic.model.AuthenticationDetails;

@Repository("commonDao")
public class CommonRepositoryImpl implements CommonRepository {

	final static Logger logger = Logger.getLogger(CommonRepositoryImpl.class);

	@Autowired
	public JdbcTemplate jdbctemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public AuthenticationDetails getAuthdetails(String userId, String password) throws Exception {
		
		logger.info("Enter into getAuthdetails:-- ");
		String SQL_AUTH_DET = "SELECT * FROM GST_AUTHTOKEN_DETAILS WHERE USERID=:userId AND PASSWORD=:password";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("password", password);
		return namedParameterJdbcTemplate.queryForObject(SQL_AUTH_DET, param, new AuthRowMapper());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveAuthdetails(AuthenticationDetails authDetails) throws Exception {
		
		logger.info("Enter into saveAuthdetails:-- ");
		
		String UPDT_AUTH_DET ="UPDATE GST_AUTHTOKEN_DETAILS SET TOKEN_TIME=?,ENCODED_APP_KEY=?,ENCRYPT_PASSWORD=?,"
				+ "ENCRYPT_SESSION_ID=?,AUTH_TOKEN=?,AUTH_SEK=?,ENCODED_EK=? WHERE USERID=? AND PASSWORD=?";
		
		boolean flag = false;
		int cnt = jdbctemplate.update(UPDT_AUTH_DET,
				new Object[] { authDetails.getToken_time(), authDetails.getEncoded_app_key(),
						authDetails.getEncrypt_password(), authDetails.getEncrypt_session_id(),
						authDetails.getAuth_token(), authDetails.getAuth_sek(), authDetails.getEncoded_ek(),
						authDetails.getUserId(), authDetails.getPassword() });
		
		logger.info("UPDATE STATUS:--"+cnt);
		if (cnt > 0) {
			flag = true;
		} else {
			flag = false;
		}
		logger.info("Exit from saveAuthdetails:-- ");
		return flag;
	}

	@Override
	public Date getR9CMaxDate() throws Exception {		
		logger.info("Enter into getR9CMaxDate:-- ");		
		String DRC07_DT = "SELECT MAX(API_DT) FROM GST_RET_R9C_API_DT";	
		Date dt=jdbctemplate.queryForObject(DRC07_DT, Date.class);	
		logger.info("Exit from getR9CMaxDate:--");
		return 	dt;			
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void R9CAPIDateInsert(String apiDt)throws Exception {
		logger.info("Enter into R9CAPIDateInsert:-- ");
		String INSRT_R9C_DT="INSERT INTO GST_RET_R9C_API_DT(API_DT) VALUES(?)";
		int cnt=jdbctemplate.update(INSRT_R9C_DT, new Object[] {apiDt});
		logger.info("INSERT STATUS:--"+cnt);
		logger.info("Exit from R9CAPIDateInsert:-- ");

	}

}
