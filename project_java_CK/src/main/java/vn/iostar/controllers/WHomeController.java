package vn.iostar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WHomeController {
	
	@GetMapping("/user")
	public String index()
	{
		return "admin/home-admin";
	}

}
