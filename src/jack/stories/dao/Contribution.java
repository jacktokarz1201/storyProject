package jack.stories.dao;


public class Contribution {
	private String users_username;
	private String stories_title;
	private String content;
	public String getUsers_username() {
		return users_username;
	}
	public void setUsers_username(String users_username) {
		this.users_username = users_username;
	}
	public String getStories_title() {
		return stories_title;
	}
	public void setStories_title(String stories_title) {
		this.stories_title = stories_title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Contribution(String users_username, String stories_title, String content) {
		super();
		this.users_username = users_username;
		this.stories_title = stories_title;
		this.content = content;
	}
	public Contribution() {
		
	}
	@Override
	public String toString() {
		return "Contribution [users_username=" + users_username + ", stories_title=" + stories_title + ", content="
				+ content + "]";
	}
	
}
