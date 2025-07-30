package gov.nic.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReturnR9Cnt {
	
	private String eod_closed;
	private Date date;
	private int num_files;
	
	public String getEod_closed() {
		return eod_closed;
	}
	public void setEod_closed(String eod_closed) {
		this.eod_closed = eod_closed;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNum_files() {
		return num_files;
	}
	public void setNum_files(int num_files) {
		this.num_files = num_files;
	}
	@Override
	public String toString() {
		return "ReturnR1Count [eod_closed=" + eod_closed + ", date=" + date
				+ ", num_files=" + num_files + "]";
	}
	

}
