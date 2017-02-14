package jack.stories.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jack.stories.dao.Contribution;
import jack.stories.dao.Story;
import jack.stories.dao.Author;
import jack.stories.service.ContributionsService;

@Controller
public class ContributionsController {

	@Autowired
	private ContributionsService contributionsService;
	
	@RequestMapping(value="/newContribution")
	public String showNewContribution(Model model) {
		model.addAttribute("contribution", new Contribution());
		return("newContribution");
	}
	
	@RequestMapping(value="/addContribution", method= RequestMethod.POST)
	public String addContribution(@Valid Contribution contribution, BindingResult result) {
		System.out.println("1 "+contribution);
		contributionsService.createContribution(contribution);
		return("completedStories");
	}
}
