package server.io.model;

public class ItemEntity {
	int itemId;
	String isbn;
	String copyNumber;

	public ItemEntity(int itemId, String ISBN, String copyNumber) {
		this.itemId = itemId;
		this.isbn = isbn;
		this.copyNumber = copyNumber;
	}
}
