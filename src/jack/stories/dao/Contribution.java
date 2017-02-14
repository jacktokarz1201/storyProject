package jack.stories.dao;


public class Contribution {
	private String addition;
	private String title;
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Contribution(String addition, String title) {
		this.addition = addition;
		this.title = title;
	}
	public Contribution() {
		
	}
	@Override
	public String toString() {
		return "Contribution [addition=" + addition + ", title=" + title + "]";
	}
}