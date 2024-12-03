package vn.iostar.controllers.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iostar.entity.User;
import vn.iostar.service.UserServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class LoginUser {
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/create")
    public String Register(Model model){
        User user = new User();
        model.addAttribute("User", user);
        return "/../user/add-user";
    }
    @PostMapping("/save")
    public String save( @ModelAttribute("User") User user){
        userService.create(user);
        return "redirect:/users/create";
    }
    @PostMapping("/login")
    public String LoginUser (@RequestBody Map<String, String> requestBody){
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        if(userService.loginUser(email, password)){
            return "true";
        }else {
            return "false";
        }
    }
}
