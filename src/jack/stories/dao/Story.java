package jack.stories.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="stories")
public class Story{

	@Size(min=3, max=40)
	@Pattern(regexp="^[a-zA-Z0-9]{1,}.*")
	@Id
	private String title;
	
	@Size(min=1)
	@Pattern(regexp="^[a-zA-Z0-9]{1,}.*")
	private String content;
	
	@Min(value=1)
	@Max(value=99)
	private int lineLength;
	
	@Min(value=1)
	@Max(value=20)
	private int storyLength;
	private boolean completed;
	private String author;
	
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

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public String getTitle() {
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
				+ completed + ", content=" + content + ", authors= "+ author +"]";
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
