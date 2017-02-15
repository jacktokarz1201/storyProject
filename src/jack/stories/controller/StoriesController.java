package jack.stories.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jack.stories.dao.AuthorsDao;
import jack.stories.dao.LoggedUser;
import jack.stories.dao.Story;

@Controller
public class StoriesController {
	
	private AuthorsDao authorsDao;
	
	@RequestMapping("/")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
		/*Because the username and passwords are simply written into the variable as variables
			it would not be difficult to change them, so this check if the saved username and
			password match, to ensure they didn't simply hack into a different username's
			account without knowing its corresponding password. For security. 	*/		
		if(LoggedUser.getUsername() != null) {
			if(!authorsDao.passwordCheck()) {
				return new ModelAndView("error", "issue", "Your username and password do not match, please re-open the window.");
			}
		}
        String now = LoggedUser.getUsername();
        System.out.println("Logged in as: "+now);
        //to show their name on the home page.
        return new ModelAndView("home", "now", now);
	}
	
	@RequestMapping("/completedStories")
	public String showCompleted() {
		return "completedStories";
	}
	

	
}
