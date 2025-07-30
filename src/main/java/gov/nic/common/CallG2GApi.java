/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.nic.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 *
 * @author user1
 */
public final class CallG2GApi {

	static Logger logger = Logger.getLogger(CallG2GApi.class);

	public final String authToken(String clientid, String client_secret,String user_name, String password, String state_cd, String app_key)	throws IOException {
		
		StringBuffer jsonString = new StringBuffer();		
		String requesturl = "https://boapi.internal.gst.gov.in/govtapi/v0.2/authenticate";
		
		try{
			
		logger.info("Enter into authToken method():--");		
		
		String payload = "{" + "\"action\":\"ACCESSTOKEN\","
				+ "\"username\":\"" + user_name + "\"," + "\"password\":\""
				+ password + "\"," + "\"app_key\":\"" + app_key + "\"" + "}";
		
		logger.info("request URL:--" + requesturl);
		logger.info("Payload:--" + payload);
		URL url = new URL(requesturl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Media-Type", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("clientid", clientid);
		connection.setRequestProperty("client-secret", client_secret);
		connection.setRequestProperty("state-cd", state_cd);	

		/* Create Payload if method is PUT or POST */
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(payload);
		writer.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			jsonString.append(line);
		}
		br.close();
		connection.disconnect();
		logger.info("Exit from authToken method():--");
		return jsonString.toString();
		}catch(Exception e){			
			logger.error("Exception Cause:"+e.toString());
			logger.error("Exception in getAPI Url:--"+requesturl);		
			throw new RuntimeException(e.getMessage());
		}
	}

	public final String getApi(String requesturl, String clientid,String client_secret, String user_name, String state_cd,String auth_token) throws UnknownHostException, MalformedURLException {
		
		
		HttpURLConnection connection=null;
		StringBuffer jsonString = new StringBuffer();
		try {	
						
			URL url = new URL(requesturl);
			connection = (HttpURLConnection) url.openConnection();
//			logger.info("status:---"+connection.getResponseCode());
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Media-Type", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("clientid", clientid);
			connection.setRequestProperty("client-secret", client_secret);
			connection.setRequestProperty("username", user_name);
			connection.setRequestProperty("state-cd", state_cd);
			connection.setRequestProperty("auth-token", auth_token);			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			
			return jsonString.toString();
		}catch (Exception e) {	
			e.printStackTrace();
	       logger.error("Exception in getAPI Url:--"+requesturl);
		   logger.error("Exception into getAPI method():--"+e.getMessage());				
		   throw new RuntimeException(e.getMessage());
		}finally{
			if(connection!=null){
			     connection.disconnect();
			}
		}

	}	

	public final String postApi(String requesturl, String payload,String clientid, String client_secret, String user_name,String state_cd, String auth_token, String method) {
		
		
		StringBuffer jsonString = new StringBuffer();
		
		try {	

			URL url = new URL(requesturl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Media-Type", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("clientid", clientid);
			connection.setRequestProperty("client-secret", client_secret);
			connection.setRequestProperty("username", user_name);
			connection.setRequestProperty("state-cd", state_cd);
			connection.setRequestProperty("auth-token", auth_token);			

			/* Create Payload if method is PUT or POST */
			String req_payload = payload;
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(req_payload);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
		} catch (Exception e) {
			logger.error("Exception in getApi:-" + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}		
		return jsonString.toString();
	}
	
	
	
}
