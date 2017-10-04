package main.java.server.io.model;

public class FeeEntity {
	private int userId;
	private int fee;

	public FeeEntity () {

	}

	public FeeEntity(int userId, int fee){
		this.userId = userId;
		this.fee = fee;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

}
