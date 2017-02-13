package jack.stories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.stories.dao.User;
import jack.stories.dao.UsersDao;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDao;
	
	
	public void createUser(User user) {
		usersDao.createUser(user);
	}

	public boolean exists(String username) {
		
		return usersDao.exists(username);
	}

}
