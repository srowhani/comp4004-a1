package server.io.model;

public class TitleEntity {
	String ISBN;
	String booktitle;
	
	public TitleEntity(String ISBN, String booktitle){
		this.ISBN=ISBN;
		this.booktitle=booktitle;
	}
}
