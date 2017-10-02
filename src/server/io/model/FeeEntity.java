package server.io.model;

public class FeeEntity {
	int userId;
	int fee;

	public FeeEntity () {

	}

	public FeeEntity(int userId, int fee){
		this.userId = userId;
		this.fee = fee;
	}
}
