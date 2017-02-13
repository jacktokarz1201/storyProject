package jack.stories.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.stories.dao.StoriesDao;
import jack.stories.dao.Story;

@Service("storiesService")
public class StoriesService {
	
	@Autowired
	private StoriesDao storiesDao;
	
	public void createStory(Story story) {
		storiesDao.createStory(story);
	}
	
	public boolean exists(String title) {
		return storiesDao.exists(title);
	}
	
	public Story getStory(String title) {
		return storiesDao.getStory(title);
	}


	public List<Story> getStories() {
		return storiesDao.getAllStories();
	}
	
}
