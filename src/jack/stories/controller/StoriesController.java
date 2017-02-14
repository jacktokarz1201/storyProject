package jack.stories.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jack.stories.dao.Story;

@Controller
public class StoriesController {
	
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	@RequestMapping("/completedStories")
	public String showCompleted() {
		return "completedStories";
	}
	

	
}
