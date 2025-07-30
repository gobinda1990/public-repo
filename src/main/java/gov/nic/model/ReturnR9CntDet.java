package gov.nic.model;

public class ReturnR9CntDet {
	private int cnt;
	private int file_num;
	private String hash;
	private String url;
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ReturnR9CntDet [cnt=" + cnt + ", file_num=" + file_num + ", url=" + url + "]";
	}
	
	

}
