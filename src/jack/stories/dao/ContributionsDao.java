package jack.stories.dao;

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
	
	@Transactional
	public boolean createContribution(Contribution contribution) {
		String originalContent = jdbc.queryForObject("select content from stories where title=:title", 
				new MapSqlParameterSource("title", contribution.getTitle()), String.class);
		
		originalContent = originalContent+ "\n" +contribution.getAddition();
		System.out.println(originalContent);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("title", contribution.getTitle());
		params.addValue("content", originalContent);

		return jdbc.update("update stories set content = :content where title= :title", params) == 1;
	}

	
}
