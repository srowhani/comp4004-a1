package server.io.model;

public class UserEntity {
	private int id;
	private String username;
	private String password;

	public UserEntity () {

	}

	public UserEntity(int id, String username, String password) {
		this.id = id;
		this.password = password;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString(){
		return String.format("[%s, %s, %s]", id, username, password);
	}
}
