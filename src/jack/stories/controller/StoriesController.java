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

import jack.stories.dao.LoggedUser;
import jack.stories.dao.Story;

@Controller
public class StoriesController {
	
	
	@RequestMapping("/")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
        String now = LoggedUser.getUsername();
        System.out.println("Logged in as: "+now);
        return new ModelAndView("home", "now", now);
	}
	
	@RequestMapping("/completedStories")
	public String showCompleted() {
		return "completedStories";
	}
	

	
}
