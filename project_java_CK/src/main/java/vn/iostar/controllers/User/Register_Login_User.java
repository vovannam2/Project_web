package vn.iostar.controllers.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iostar.entity.User;
import vn.iostar.service.User.UserServiceImpl;

import java.util.Map;

//@RestController
@Controller
@RequestMapping("/users")
public class Register_Login_User {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/register")
    public String Register(Model model){
        User user = new User();
        model.addAttribute("User", user);
        return "user/create_user";
    }
    @PostMapping("/save")
    public String save( @ModelAttribute("User") User user, Model model){
        userService.create(user);
        return "redirect:/users/login_user";
    }
    @GetMapping("/login_user")
    public String showLoginForm(Model model) {
        model.addAttribute("alert", "đã đăng kí thành công, vui lòng nhập mật khẩu đã cung cấp tại gmail!"); // Dùng để hiển thị thông báo nếu cần
        return "user/Login-user"; // Trả về tên template Thymeleaf (login.html)
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            RedirectAttributes redirectAttributes) {
        if (userService.loginUser(email, password)) {
            User user = userService.getUser(email);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/home"; // Chuyển hướng đến trang home nếu đăng nhập thành công
        } else {
            redirectAttributes.addFlashAttribute("alert", "Sai mật khẩu. Vui lòng thử lại!");
            return "redirect:/login_user"; // Quay lại form đăng nhập với thông báo lỗi
        }
    }
}
