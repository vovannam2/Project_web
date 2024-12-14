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
import jakarta.servlet.http.HttpSession;
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
		/*
		 * String username = req.getParameter("username"); String password =
		 * req.getParameter("password");
		 * 
		 * Optional<User> userOptional = userService.findByUsername(username);
		 * 
		 * if (userOptional.isPresent()) { User user = userOptional.get();
		 * 
		 * if (user.getUsername().equals(username) &&
		 * user.getPassword().equalsIgnoreCase(password)) { // Lưu thông tin user vào
		 * session HttpSession session = req.getSession();
		 * session.setAttribute("currentUser", user);
		 * 
		 * // Chuyển hướng đến trang index return "redirect:/shipper/listPacel";
		 * //return "shipper/home-shipper"; } }
		 * 
		 * // Sai tài khoản hoặc mật khẩu, hiển thị thông báo lỗi
		 * model.addAttribute("message", "Invalid credentials");
		 */
	    return "security/login";
	}

	@GetMapping("register")
	public String IndexRegister(ModelMap model) {
		return "security/register";
	}
}
