package gov.nic.repository;

import java.util.Date;

import gov.nic.model.AuthenticationDetails;

public interface CommonRepository {

	public AuthenticationDetails getAuthdetails(String userId, String password)throws Exception;

	public boolean saveAuthdetails(AuthenticationDetails authDetails)throws Exception;

	public Date getR9CMaxDate()throws Exception;

	public void R9CAPIDateInsert(String startTm)throws Exception;
}
