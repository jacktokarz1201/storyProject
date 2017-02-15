package jack.stories.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("contributionsDao")
public class ContributionsDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	//adds the content of the contribution to the corresponding story entry, reduces the number of lines left to
		//enter by 1, if its at 0, marks the story as complete. Also updates authors section.
	@Transactional
	public boolean createContribution(Contribution contribution) {
		
		System.out.println("contributing author is: "+contribution.getAuthor());
		
		String originalContent = jdbc.queryForObject("select content from stories where title=:title", 
				new MapSqlParameterSource("title", contribution.getTitle()), String.class);
		String previousAuthors = jdbc.queryForObject("select authors from stories where title=:title", 
				new MapSqlParameterSource("title", contribution.getTitle()), String.class);
		int remaining= jdbc.queryForObject("select storyLength from stories where title=:title", 
				new MapSqlParameterSource("title", contribution.getTitle()), Integer.class) - 1;
		boolean isComplete = jdbc.queryForObject("select completed from stories where title=:title", 
				new MapSqlParameterSource("title", contribution.getTitle()), Boolean.class);
		if(remaining <= 0) {
			isComplete = true;
		}
		originalContent = originalContent+ "\n" +contribution.getAddition();
		previousAuthors = previousAuthors+contribution.getAuthor()+",";
		
		System.out.println(originalContent+" remaining: "+remaining);
		System.out.println("list of authors: "+previousAuthors);
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("title", contribution.getTitle());
		params.addValue("content", originalContent);
		params.addValue("storyLength", remaining);
		params.addValue("completed", isComplete);
		params.addValue("authors", previousAuthors);

		return jdbc.update("update stories set content = :content, storyLength = :storyLength, completed = :completed, authors = :authors where title= :title", params) == 1;
	}

	public boolean exists(String title) {
		return jdbc.queryForObject("select count(*) from stories where title=:title", 
				new MapSqlParameterSource("title", title), Integer.class) > 0;
	}
	
	//compares the length of the proposed addition to the maximum allowed.
	public boolean checkLength(Contribution contribution) {
		int longness= jdbc.queryForObject("select lineLength from stories where title=:title", 
				new MapSqlParameterSource("title", contribution.getTitle()), Integer.class);
		System.out.println("Your content: "+contribution.getAddition().length()+" the max length: "+longness);
		if(contribution.getAddition().length() > longness) {
			return false;
		}
		return true;
	}
	
	//true if this user has already added to the story (should be impossible, but making sure)
	public boolean checkRepeat(Contribution contribution) {
		
		System.out.println("Checking against "+contribution.getAuthor());
		
		String previousAuthors = jdbc.queryForObject("select authors from stories where title=:title", 
				new MapSqlParameterSource("title", contribution.getTitle()), String.class);
		String[] eachAuthor = previousAuthors.split(",");
		for(String name: eachAuthor) {
			System.out.println(name);
			if(contribution.getAuthor().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
