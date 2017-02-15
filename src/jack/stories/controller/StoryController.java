package jack.stories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String createStory(@Validated(FormValidationGroup.class) Story story, BindingResult result) {
		
		System.out.println(story);
		if(result.hasErrors()) {
			return "error";
		}
		if(storiesService.exists(story.getTitle())) {
			result.rejectValue("title", "DuplicateKey.story.title");
			return "error";
		}
		
		try {
			storiesService.createStory(story);
		} catch (DuplicateKeyException e) {
			result.rejectValue("title", "DuplicateKey.story.title");
			return "error";
		}
		
		return "storyCreated";
	}
	
	@RequestMapping("/storyCreated")
	public String showStoryCreated() {
		return "storyCreated";
	}
	
}
