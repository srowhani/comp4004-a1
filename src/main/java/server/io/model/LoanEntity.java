package main.java.server.io.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanEntity {
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private int userId;
	private String isbn;
	private String copyNumber;
	private Date date;
	private String renewState;

	public LoanEntity () {

	}

	public LoanEntity(int userId, String isbn, String copyNumber, Date date, String renewState) {
		this.userId = userId;
		this.isbn = isbn;
		this.copyNumber = copyNumber;
		this.date = date;
		this.renewState = renewState;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public String getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRenewState() {
		return renewState;
	}

	public void setRenewState(String renewState) {
		this.renewState = renewState;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s, %s]",
				userId, isbn, copyNumber, dateFormat.format(date), renewState);
	}
}
