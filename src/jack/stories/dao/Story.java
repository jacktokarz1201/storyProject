package jack.stories.dao;

import javax.persistence.Entity;


public class Story{

	private String title;
	private int lineLength;
	private int storyLength;
	private boolean completed;
	private String content;
	
	
	public Story() {
		
	}	
	public Story(String title, int lineLength, int storyLength, boolean completed, String content) {
		super();
		this.title = title;
		this.lineLength = lineLength;
		this.storyLength = storyLength;
		this.completed = completed;
		this.content = content;
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

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Story [title=" + title + ", lineLength=" + lineLength + ", storyLength=" + storyLength + ", completed="
				+ completed + ", content=" + content + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + lineLength;
		result = prime * result + storyLength;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Story other = (Story) obj;
		if (completed != other.completed)
			return false;
		if (lineLength != other.lineLength)
			return false;
		if (storyLength != other.storyLength)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
