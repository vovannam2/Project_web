package vn.iostar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.iostar.entity.User;
import vn.iostar.service.User.UserServiceImpl;

import java.util.List;


@Controller
public class WHomeController {
	@Autowired
	UserServiceImpl userService;
	@GetMapping("/home")
	public String index(Model model)
	{
		List<User> categories = userService.findAll();
		model.addAttribute("list", categories);
		return "/user/home-user";
	}

}
