package jack.stories.controller;

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
import jack.stories.dao.Author;
import jack.stories.service.ContributionsService;

@Controller
public class ContributionsController {

	private ContributionsService contributionsService;
	
	@Autowired
	public void setContributionsService(ContributionsService contributionsService) {
		this.contributionsService = contributionsService;
	}
	
	@RequestMapping(value="/newContribution")
	public String showNewContribution(Model model) {
		model.addAttribute("contribution", new Contribution());
		return("newContribution");
	}
	
	@RequestMapping(value="/addContribution", method= RequestMethod.POST)
	public ModelAndView addContribution(@Valid Contribution contribution, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("newContribution");
		}
		//if that story does not exist
		if(!contributionsService.exists(contribution.getTitle())) {
			return new ModelAndView("newContribution", "error", "That story does not exist.");
		}
		//makes sure they are logged in.
		if(LoggedUser.getUsername()==null) {
			System.out.println("it's null!");
			return new ModelAndView("newContribution", "error", "You must be logged in to contribute to a story.");
		}
		else {
			contribution.setAuthor(LoggedUser.getUsername());
			System.out.println(contributionsService.checkRepeat(contribution));
			if(contributionsService.checkRepeat(contribution)) {
				return new ModelAndView("newContribution", "error", "You have already added to this story.");
			}
		}
		//if their line is too long
		if(!contributionsService.checkLength(contribution)) {
			return new ModelAndView("newContribution", "error", "Your line is longer than the maximum line length!");
		}
		//in case something weird happens.
		try {
			contributionsService.createContribution(contribution);
		} catch (DuplicateKeyException e) {
			return new ModelAndView("newContribution", "error", "Something odd has happened, and your addition cannot be made: "+e.getMessage());
		}
		
		return new ModelAndView("contributionAdded", "name", contribution.getTitle());		
		
	}
}
