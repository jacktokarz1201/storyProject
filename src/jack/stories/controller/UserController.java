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
	public String createUser(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		
		System.out.println(user);
		if(result.hasErrors()) {
			return "home";
		}
		if(usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "home";
		}
		
		try {
			usersService.createUser(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("title", "DuplicateKey.user.username");
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
	
	
}
