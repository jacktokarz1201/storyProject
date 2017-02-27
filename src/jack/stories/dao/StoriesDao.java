package jack.stories.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("storiesDao")
@Transactional
public class StoriesDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	SessionFactory sessionFactory;
	
	public Session sess() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public Story updateStory(Story story) {
		story.setStoryLength(story.getStoryLength()-1);
		story.setCompleted(story.getStoryLength()==0);
		story.setAuthor(story.getAuthor().concat(","));
		return story;
	}
	
	//Just trying out a little Hibernate, but I don't want to use it for everything, just want to prove I can get it to work.
	@Transactional
	public boolean createStory(Story story) {
//		story = updateStory(story);
//		return sess().save(story) != null;
		
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
//		return sess().createQuery("from Story").list();
		return jdbc.query("select * from stories", BeanPropertyRowMapper.newInstance(Story.class));
	}
	//returns true if one or more entries in the table have matching username and password.
	public boolean passwordCheck() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", LoggedUser.getUsername());
		params.addValue("password", LoggedUser.getPassword());
		return jdbc.queryForObject("select count(*) from authors where username=:username and password=:password", 
				params, Integer.class) > 0;
	}
	
}
