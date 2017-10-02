package server.io.model;

public class ItemEntity {
	private int itemId;
	private String isbn;
	private String copyNumber;

	public ItemEntity() {

	}

	public ItemEntity(int itemId, String ISBN, String copyNumber) {
		this.itemId = itemId;
		this.isbn = isbn;
		this.copyNumber = copyNumber;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
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

}
