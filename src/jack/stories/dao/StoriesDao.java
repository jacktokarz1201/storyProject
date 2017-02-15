package jack.stories.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("storiesDao")
public class StoriesDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Transactional
	public boolean createStory(Story story) {
		int updatedLength= story.getStoryLength()-1;
		boolean isComplete = updatedLength == 0;
		String authorPlus = story.getAuthor().concat(",");
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("title", story.getTitle());
		params.addValue("lineLength", story.getLineLength());
		params.addValue("storyLength", updatedLength);
		params.addValue("completed", isComplete);
		params.addValue("content", story.getContent());
		params.addValue("authors", authorPlus);
		
		return jdbc.update("insert into stories (title, lineLength, storyLength, completed, content, authors) values (:title, :lineLength, :storyLength, :completed, :content, :authors)", params) == 1;
	}
	
	public boolean exists(String title) {
		return jdbc.queryForObject("select count(*) from stories where title=:title", 
				new MapSqlParameterSource("title", title), Integer.class) > 0;
	}

	public List<Story> getAllStories() {
		return jdbc.query("select * from stories", BeanPropertyRowMapper.newInstance(Story.class));
	}
	
	
}
