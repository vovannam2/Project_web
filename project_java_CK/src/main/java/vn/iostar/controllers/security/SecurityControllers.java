package vn.iostar.controllers.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import vn.iostar.entity.User;
import vn.iostar.service.user.IUserService;

@Controller
@RequestMapping("security")
public class SecurityControllers {
	@Autowired IUserService userService;
	
	@GetMapping("login")
	public String IndexLogin(ModelMap model) {
		//model.addAttribute("username", cookieService.getValue("username"));
		//model.addAttribute("password", cookieService.getValue("password"));
		return "security/login";
	}
	
	@PostMapping("login")
	public String login(ModelMap model, HttpServletRequest req) throws MessagingException {
		String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    
	    Optional<User> userOptional = userService.findByUsername(username);
	    
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        
	        if (user.getUsername().equals(username) && user.getPassword().equalsIgnoreCase(password)) {
	            // Correct username and password
	           
	            return "user/index";
	        }
	    }
	    
	    // Incorrect username or password, handle accordingly (e.g., show error message)
	    model.addAttribute("message", "Invalid credentials");
	    return "security/login"; // Adjust the view name accordingly
	}
	@GetMapping("register")
	public String IndexRegister(ModelMap model) {
		return "security/register";
	}
}
