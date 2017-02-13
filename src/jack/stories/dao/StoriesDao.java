package jack.stories.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("storiesDao")
public class StoriesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	public void createStory(Story story) {
		session().save(story);
	}
	
	public boolean exists(String title) {
		System.out.println(" 3");
		Story story = getStory(title);
		if(story != null) {
			return true;
		}
		else
			return false;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Story> getAllStories() {
		return session().createQuery("from stories").list();
	}
	
	public Story getStory(String title) {
		CriteriaQuery<Story> cq = session().getCriteriaBuilder().createQuery(Story.class);
		cq.from(Story.class);
		List<Story> storiesList = session().createQuery(cq).getResultList();
		System.out.println(title);
		for(Story entry : storiesList) {
			if(entry.getTitle() == title) {
				return entry;
			}
		}
		return null;
	}
	
}
