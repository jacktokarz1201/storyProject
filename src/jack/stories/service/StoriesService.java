package jack.stories.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.stories.dao.StoriesDao;
import jack.stories.dao.Story;

@Service("storiesService")
public class StoriesService {
	
	private StoriesDao storiesDao;
	
	@Autowired
	public void setStoriesDao(StoriesDao storiesDao) {
		this.storiesDao = storiesDao;
	}

	public void createStory(Story story) {
		storiesDao.createStory(story);
	}
	
	public boolean exists(String title) {
		return storiesDao.exists(title);
	}


	public List<Story> getStories() {
		return storiesDao.getAllStories();
	}
	
	public boolean passwordCheck() {
		return storiesDao.passwordCheck();
	}
}
