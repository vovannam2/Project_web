package vn.iostar.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iostar.entity.User;
import vn.iostar.service.User.CreateUsersServiceImpl;
import vn.iostar.service.User.UserFunctionServiceImpl;

import java.util.List;


@Controller
@RequestMapping("/home")
public class WHomeController {
	@Autowired
	CreateUsersServiceImpl userService;
	@Autowired
	UserFunctionServiceImpl functionService;

	@GetMapping("/home_user")
	public String homeUser(Model model, HttpServletRequest request)
	{
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("user",user);
		if(user!= null) {
			System.out.println(user.getUserId());
			request.getSession().setAttribute("user", user);
			List<Object[]> productDetails = functionService.getUserWithParcelsAndProducts(user.getUserId());
			model.addAttribute("productDetails", productDetails);
		}
		request.getSession().setAttribute("user", user);
		return "/user/home-user";
	}
	@GetMapping("/home_shipper")
	public String homeShipper(Model model)
	{
		List<User> categories = userService.findAll();
		model.addAttribute("list", categories);
		return "/admin/home-admin";
	}
	@GetMapping("/Contact_manager")
	public String loadChat() {
		return "user/Contact_manager";
	}
}
