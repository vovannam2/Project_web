package vn.iostar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.entity.User;
import vn.iostar.service.User.CreateUsersServiceImpl;
import vn.iostar.service.User.UserFunctionServiceImpl;

import java.util.List;


@Controller
public class WHomeController {
	@Autowired
	CreateUsersServiceImpl userService;
	@Autowired
	UserFunctionServiceImpl functionService;

	@GetMapping("/home_user")
	public String homeUser(Model model)
	{
		List<User> categories = userService.findAll();
		model.addAttribute("list", categories);
		functionService.print();
		return "/user/home-user";
	}
	@GetMapping("/home_shipper")
	public String homeShipper(Model model)
	{
		List<User> categories = userService.findAll();
		model.addAttribute("list", categories);
		return "/admin/home-admin";
	}
}
