package jack.stories.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jack.stories.dao.Contribution;
import jack.stories.dao.LoggedUser;
import jack.stories.dao.Story;
import jack.stories.service.ContributionsService;

@Controller
public class ContributionsController {

	private ContributionsService contributionsService;
	
	@Autowired
	public void setContributionsService(ContributionsService contributionsService) {
		this.contributionsService = contributionsService;
	}
	/*
	@RequestMapping(value="/newContribution")
	public String showNewContribution(Model model) {
		model.addAttribute("contribution", new Contribution());
		return("newContribution");
	}
	*/
	@RequestMapping(value="/storySelected", method=RequestMethod.POST)
	public ModelAndView storySelected(@Valid Contribution contribution, BindingResult result, Model model) {
		if(!contributionsService.exists(contribution.getTitle())) {
			return new ModelAndView("error", "error", "There was an error and we did not get a title.");
		}
		Story story = new Story();
		story = contributionsService.getStory(contribution.getTitle());
		Map<String, String> message = contributionsService.makeMessage(story);
		
		model.addAttribute("contribution", new Contribution());
		return new ModelAndView("/newContribution", "message", message);
	}
	
	@RequestMapping(value="/addContribution", method= RequestMethod.POST)
	public ModelAndView addContribution(@Valid Contribution contribution, BindingResult result) {
		//just in case there's an error
		Story story = new Story();
		story = contributionsService.getStory(contribution.getTitle());
		Map<String, String> message = contributionsService.makeMessage(story);
		//if the contribution is blank or doesn't have any letters or numbers
		if(contribution.getAddition()==null || contribution.getAddition().length() < 1 || !contribution.getAddition().matches("^[a-zA-Z0-9]{1,}.*")) {
			message.put("error", "Your addition has to start with a letter or number.");
			return new ModelAndView("newContribution", "message", message);
		}
		//if that story does not exist
		if(!contributionsService.exists(contribution.getTitle())) {
			message.put("error", "That story does not exist.");
			return new ModelAndView("newContribution", "message", message);
		}
		//makes sure they are logged in.
		if(LoggedUser.getUsername()==null) {
			message.put("error", "You must be logged in to contribute to a story.");
			return new ModelAndView("newContribution", "message", message);
		}
		//makes sure their username and password match.
		else if(contributionsService.passwordCheck()==false) {
			return new ModelAndView("error", "error", "You are not logged in correctly, please log in again or reset the window..");
		}
		else {
			contribution.setAuthor(LoggedUser.getUsername());
			if(contributionsService.checkRepeat(contribution)) {
				message.put("error", "You have already added to this story.");
				return new ModelAndView("newContribution", "message", message);
			}
		}
		//if their line is too long
		if(!contributionsService.checkLength(contribution)) {
			message.put("error", "Your line is longer than the maximum line length!");
			return new ModelAndView("newContribution", "message", message);
		}
		//in case something weird happens.
		try {
			contributionsService.createContribution(contribution);
		} catch (DuplicateKeyException e) {
			message.put("error", "Something odd has happened, and your addition cannot be made.");
			return new ModelAndView("newContribution", "message", message);
		}
		
		return new ModelAndView("contributionAdded", "name", contribution.getTitle());		
		
	}
	@RequestMapping(value="/storiesInProgress")
	public ModelAndView showStoriesInProgress(Model model) {
		if(LoggedUser.getUsername()==null) {
			return new ModelAndView("error", "error", "You must be logged in to add to a story.");
		}
		model.addAttribute("contribution", new Contribution());
		return new ModelAndView("storiesInProgress", "user", LoggedUser.getUsername());
	}
}
