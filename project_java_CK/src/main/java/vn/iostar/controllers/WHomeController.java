package vn.iostar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WHomeController {
	
	@GetMapping("/")
	public String index()
	{
		return "user/index";
	}

}
