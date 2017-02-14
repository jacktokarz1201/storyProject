package jack.stories.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jack.stories.dao.Contribution;

@Controller
public class ContributionsController {

	@RequestMapping(value="/newContribution")
	public String showNewContribution(Model model) {
		model.addAttribute("contribution", new Contribution());
		return("newContribution");
	}
}
