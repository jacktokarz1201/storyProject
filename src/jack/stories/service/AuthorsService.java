package jack.stories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.stories.dao.Author;
import jack.stories.dao.AuthorsDao;

@Service("authorsService")
public class AuthorsService {

	@Autowired
	private AuthorsDao authorsDao;
	
	
	public void createAuthor(Author author) {
		
		authorsDao.createAuthor(author);
	}

	public boolean exists(String username) {
		return authorsDao.exists(username);
	}

	public boolean exists(String username, String password) {
		return authorsDao.exists(username, password);
	}

	public boolean passwordCheck() {
		return authorsDao.passwordCheck();
	}

}
