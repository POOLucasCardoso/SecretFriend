
public class Friend {
	
	private String name;
	private String email;
	private String friendEmail;

	public Friend(String name, String email, String friendEmail) {
		super();
		this.name = name;
		this.email = email;
		this.friendEmail = friendEmail;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFriendEmail() {
		return friendEmail;
	}


	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}

}
