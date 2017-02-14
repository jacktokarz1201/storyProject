package jack.stories.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class User {

	@NotBlank(message= "Username cannot be blank.")
	@Size(min= 4, max=20, message="Username must be between 4 and 20 characters.")
	@Pattern(regexp="^\\w{4,}$", message= "Usernames may only include letters, numbers and underscores.")
	private String username;
	
	@NotBlank(message= "Password cannot be blank.")
	@Size(min= 4, max=15, message="Password must be between 4 and 15 characters.")
	@Pattern(regexp="^\\w{4,}$", message= "Passwords may only include letters, numbers and underscores.")
	private String password;
	
	private Boolean enabled;
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
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = true;
	}
	
	public User() {
		this.enabled = true;
		
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
