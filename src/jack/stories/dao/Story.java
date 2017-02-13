package jack.stories.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Story{

	private String title;
	private int lineLength;
	private int storyLength;
	private boolean completed;
	
	public Story() {
		
	}
	public Story(String title, int lineLength, int lines) {
		super();
		this.title = title;
		this.lineLength = lineLength;
		this.storyLength = lines;
		this.completed = false;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public String getTitle() {
		System.out.println(" 2");
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLineLength() {
		return lineLength;
	}
	public void setLineLength(int lineLength) {
		this.lineLength = lineLength;
	}
	public int getStoryLength() {
		return storyLength;
	}
	public void setStoryLength(int lines) {
		this.storyLength = lines;
	}
	@Override
	public String toString() {
		return "Story [title=" + title + ", lineLength=" + lineLength + ", storyLength=" + storyLength + ", completed=" + completed
				+ "]";
	}
}
