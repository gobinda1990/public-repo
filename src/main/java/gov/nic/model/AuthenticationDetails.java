package gov.nic.model;

public class AuthenticationDetails {
	
	private String userId;
	private String password;
	private String encoded_app_key;	
	private String encrypt_password;
	private String encrypt_session_id;
	private String auth_token;
	private String auth_sek;
	private String encoded_ek;
	private String token_time;
	public String getToken_time() {
		return token_time;
	}
	public void setToken_time(String token_time) {
		this.token_time = token_time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncoded_app_key() {
		return encoded_app_key;
	}
	public void setEncoded_app_key(String encoded_app_key) {
		this.encoded_app_key = encoded_app_key;
	}
	public String getEncrypt_password() {
		return encrypt_password;
	}
	public void setEncrypt_password(String encrypt_password) {
		this.encrypt_password = encrypt_password;
	}
	public String getEncrypt_session_id() {
		return encrypt_session_id;
	}
	public void setEncrypt_session_id(String encrypt_session_id) {
		this.encrypt_session_id = encrypt_session_id;
	}
	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	public String getAuth_sek() {
		return auth_sek;
	}
	public void setAuth_sek(String auth_sek) {
		this.auth_sek = auth_sek;
	}
	public String getEncoded_ek() {
		return encoded_ek;
	}
	public void setEncoded_ek(String encoded_ek) {
		this.encoded_ek = encoded_ek;
	}
/*	@Override
	public String toString() {
		return "AuthenticationDetails [userId=" + userId + ", encoded_app_key="
				+ encoded_app_key + ", encrypt_password=" + encrypt_password
				+ ", encrypt_session_id=" + encrypt_session_id
				+ ", auth_token=" + auth_token + ", auth_sek=" + auth_sek
				+ ", encoded_ek=" + encoded_ek + "]";
	}*/
	@Override
	public String toString() {
		return "AuthenticationDetails [userId=" + userId + ", password=" + password + ", encoded_app_key="
				+ encoded_app_key + ", encrypt_password=" + encrypt_password + ", encrypt_session_id="
				+ encrypt_session_id + ", auth_token=" + auth_token + ", auth_sek=" + auth_sek + ", encoded_ek="
				+ encoded_ek + ", token_time=" + token_time + "]";
	}
	
	

}
