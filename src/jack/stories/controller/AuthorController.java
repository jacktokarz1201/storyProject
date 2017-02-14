package jack.stories.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jack.stories.dao.FormValidationGroup;
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
	public String showLogin() {
		return("login");
	}
	
	@RequestMapping(value= "/register")
	public String showRegister(Model model) {
		model.addAttribute("author", new Author());
		return "register";
	}
	
	@RequestMapping(value="/loggedIn")
	public String showloggedIn() {
		return("loggedIn");
	}
	
}
