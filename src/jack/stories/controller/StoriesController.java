package jack.stories.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoriesController {
	
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
}
