package jack.stories.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.spring.web.dao.Offer;

@Component("storiesDao")
public class StoriesDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Transactional
	public boolean createStory(Story story) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("title", story.getTitle());
		params.addValue("lineLength", story.getLineLength());
		params.addValue("storyLength", story.getStoryLength());
		params.addValue("completed", false);
		
		return jdbc.update("insert into stories (title, lineLength, storyLength, completed) values (:title, :lineLength, :storyLength, :completed)", params) == 1;
	}
	
	public boolean exists(String title) {
		return jdbc.queryForObject("select count(*) from stories where title=:title", 
				new MapSqlParameterSource("title", title), Integer.class) > 0;
	}

	public List<Story> getAllStories() {
		return jdbc.query("select * from stories", BeanPropertyRowMapper.newInstance(Story.class));
	}
	
	
}
