package jack.stories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jack.stories.dao.FormValidationGroup;
import jack.stories.dao.Story;
import jack.stories.service.StoriesService;

@Controller
public class StoryController {
	
	private StoriesService storiesService;
	
	@Autowired
	public void setStoriesService(StoriesService storiesService) {
		this.storiesService = storiesService;
	}
	
	@RequestMapping(value="/newStory")
	public String showNewStory(Model model) {
		model.addAttribute("story", new Story());
		return "newStory";
	}

	@RequestMapping(value= "/createStory", method=RequestMethod.POST)
	public ModelAndView createStory(@Validated(FormValidationGroup.class) Story story, BindingResult result) {
		
		//the restraints on the components of a story
		System.out.println(story);
		if(result.hasErrors()) {
			return new ModelAndView("newStory");
		}
		//if that story already exists
		if(storiesService.exists(story.getTitle())) {
			return new ModelAndView("newStory", "error", "Sorry, a story already has that title.");
		}
		//if their first line is too long
		if(story.getContent().length() > story.getLineLength()) {
			return new ModelAndView("newStory", "error", "Follow your own rules, your line is longer than your chosen line length!");
		}
		//in case something weird happens.
		try {
			storiesService.createStory(story);
		} catch (DuplicateKeyException e) {
			return new ModelAndView("newStory", "error", "Something odd has happened, and your story cannot be made: "+e.getMessage());
		}
		
		return new ModelAndView("storyCreated", "name", story.getTitle());
	}
	
	@RequestMapping("/storyCreated")
	public String showStoryCreated() {
		return "storyCreated";
	}
	
}
