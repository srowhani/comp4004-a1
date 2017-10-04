package main.java.server.io.model;

public class TitleEntity {
	private String ISBN;
	private String booktitle;

	public TitleEntity () {
		
	}

	public TitleEntity(String ISBN, String booktitle){
		this.ISBN=ISBN;
		this.booktitle=booktitle;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String toString() {
		return String.format("[%s,%s]", ISBN, booktitle);
	}

}
