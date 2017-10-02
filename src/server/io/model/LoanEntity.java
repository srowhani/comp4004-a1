package server.io.model;

import java.util.Date;

public class LoanEntity {
	int userId;
	String isbn;
	String copyNumber;
	Date date;
	String renewState;

	public LoanEntity(int userId, String isbn, String copyNumber, Date date, String renewState) {
		this.userId = userId;
		this.isbn = isbn;
		this.copyNumber = copyNumber;
		this.date = date;
		this.renewState = renewState;
	}
}
