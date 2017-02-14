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
import jack.stories.dao.User;
import jack.stories.service.UsersService;

@Controller
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	public void setUserService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(value= "/createUser", method=RequestMethod.POST)
	public String createUser(@Valid User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "register";
		}
		if(usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "register";
		}
		
		try {
			usersService.createUser(user);
			System.out.println(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "home";
		}
		
		return "userCreated";
	}
	
	@RequestMapping(value="/login")
	public String showLogin() {
		return("login");
	}
	
	@RequestMapping(value= "/register")
	public String showRegister(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/loggedIn")
	public String showloggedIn() {
		return("loggedIn");
	}
	
}
