package vn.iostar.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
	
	@GetMapping("/shipper")
	public String index()
	{
		return "shipper/home-shipper";
	}
	@GetMapping("/Contact_manager")
	public String loadChat() {
		return "user/Contact_manager";
	}

}
