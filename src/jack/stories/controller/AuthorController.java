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
	public ModelAndView createAuthor(@Valid Author author, BindingResult result) {
		System.out.println("password: "+author.getPassword()+" confirm: "+author.getConfirm());
		//if the entered values don't match the criteria in the Author class (bean)
		if(result.hasErrors()) {
			return new ModelAndView("register");
		}
		//if that username already exists. Kind of janky execution...
		if(authorsService.exists(author.getUsername())) {
			return new ModelAndView("register", "error", "That username already exists, please choose another.");
		}
		//if the confirm password does not match the password
		if(!author.getPassword().equals(author.getConfirm())) {
			return new ModelAndView("register", "error", "Make sure you type the same thing in password and confirm password.");
		}
		
		//in the event of an unexpected error, so the site won't crash.
		try {
			authorsService.createAuthor(author);
			System.out.println(author);
		} catch (DuplicateKeyException e) {
			return new ModelAndView("register", "error", "There has been an unexpected error registering you: "+e.getMessage());
		}
		
		return new ModelAndView("createdAuthor", "name", author.getUsername());
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
	public ModelAndView showCheckLogin(@Valid Author author, BindingResult result) {
		if(authorsService.exists(author.getUsername(), author.getPassword())) {
			LoggedUser.setPassword(author.getPassword());
			LoggedUser.setUsername(author.getUsername());
			System.out.println("logged in as: "+LoggedUser.getUsername()+" with password: "+LoggedUser.getPassword());
			return new ModelAndView("loggedIn", "user", LoggedUser.getUsername());
		}
		return new ModelAndView("login", "error", "Either the username or password you entered do not match our records.");
	}	
	
	@RequestMapping(value= "/register")
	public String showRegister(Model model) {
		model.addAttribute("author", new Author());
		return "register";
	}
	/*
	@RequestMapping(value="/loggedIn")
	public ModelAndView showloggedIn() {
		System.out.println("logged in as: "+LoggedUser.getUsername());
		String pass = LoggedUser.getUsername();
		return new ModelAndView("loggedIn", "loggedInAs", pass);
	}
	*/
	
}
