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

import jack.stories.dao.FormValidationGroup;
import jack.stories.dao.LoggedUser;
import jack.stories.dao.Author;
import jack.stories.service.AuthorsService;

@Controller
public class AuthorController {
		
	@Autowired
	private AuthorsService authorsService;
	
	public void setauthorService(AuthorsService authorsService) {
		this.authorsService = authorsService;
	}

	@RequestMapping(value= "/createAuthor", method=RequestMethod.POST)
	public String createAuthor(@Valid Author author, BindingResult result) {
		
		if(result.hasErrors()) {
			return "register";
		}
		if(authorsService.exists(author.getUsername())) {
			result.rejectValue("username", "DuplicateKey.author.username");
			return "register";
		}
		
		try {
			authorsService.createAuthor(author);
			System.out.println(author);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.author.username");
			return "home";
		}
		
		return "createdAuthor";
	}
	
	@RequestMapping(value="/createdAuthor")
	public String showcreatedAuthor() {
		return("createdAuthor");
	}
	
	@RequestMapping(value="/login")
	public String showLogin(Model model) {
		model.addAttribute("author", new Author());
		return("login");
	}
	
	@RequestMapping(value="/checkLogin", method=RequestMethod.POST)
	public String showCheckLogin(@Valid Author author, BindingResult result) {
		if(authorsService.exists(author.getUsername(), author.getPassword())) {
			LoggedUser.setPassword(author.getPassword());
			LoggedUser.setUsername(author.getUsername());
			System.out.println("logged in as: "+LoggedUser.getUsername());
			return "loggedIn";
		}
		return("login?error=true");
	}	
	
	@RequestMapping(value= "/register")
	public String showRegister(Model model) {
		model.addAttribute("author", new Author());
		return "register";
	}
	
	@RequestMapping(value="/loggedIn")
	public ModelAndView showloggedIn() {
		System.out.println("logged in as: "+LoggedUser.getUsername());
		String pass = LoggedUser.getUsername();
		return new ModelAndView("loggedIn", "loggedInAs", pass);
	}
	
}
