package jack.stories.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="Authors")
public class Author {

	@NotBlank(message= "Username cannot be blank.")
	@Size(min= 4, max=20, message="Username must be between 4 and 20 characters.")
	@Pattern(regexp="^\\w{4,}$", message= "Usernames may only include letters, numbers and underscores.")
	@Id
	@Column(name="username")
	private String username;
	
	@NotBlank(message= "Password cannot be blank.")
	@Size(min= 4, max=15, message="Password must be between 4 and 15 characters.")
	@Pattern(regexp="^\\w{4,}$", message= "Passwords may only include letters, numbers and underscores.")
	@Column(name="password")
	private String password;
	
	private String confirm;
	
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
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
	public Author(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Author() {
	}
	@Override
	public String toString() {
		return "Author [username=" + username + ", password=" + password + "]";
	}
	
}
