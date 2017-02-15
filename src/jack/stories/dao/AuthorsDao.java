package jack.stories.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("authorsDao")
public class AuthorsDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Transactional
	public boolean createAuthor(Author author) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("username", author.getUsername());
		params.addValue("password", author.getPassword());
		
		return jdbc.update("insert into authors (username, password) values (:username, :password)", params) == 1;
	}

	public List<Author> getAllAuthors() {
		return jdbc.query("select * from authors", BeanPropertyRowMapper.newInstance(Author.class));
	}

	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from authors where username=:username", 
				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}
	
	public boolean exists(String username, String password) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("username", username);
		params.addValue("password", password);
		return jdbc.queryForObject("select count(*) from authors where username=:username and password=:password", 
				params, Integer.class) > 0;
	}
	
	//sees if the saved values for their username and password match what's in the table. To prevent fraud.
	public boolean passwordCheck() {
		System.out.println("Password is: "+LoggedUser.getPassword());
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", LoggedUser.getUsername());
		params.addValue("password", LoggedUser.getPassword());
		return jdbc.queryForObject("select count(*) from authors where username=:username and password=:password", 
				params, Integer.class) > 0;
	}
	
}
