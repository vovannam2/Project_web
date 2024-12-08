package vn.iostar.controllers.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;
import vn.iostar.model.ParcelRouteModel;
import vn.iostar.service.User.UserFunctionServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// function user - forget pass -
@Controller
@RequestMapping("/users_handle")
public class UserFunction {
    @Autowired
    UserFunctionServiceImpl userService;

    @PostMapping("/search")
    public String forgetPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes ){
        User resultUser = userService.checkEmailExist(email);
        if(resultUser != null){
            redirectAttributes.addFlashAttribute("user", resultUser);
            return "redirect:/users_handle/Forget_Password";
        }else {
            redirectAttributes.addFlashAttribute("alert", "Email chưa đăng kí!");
            return "redirect:/users_handle/search_user"; // Quay lại form đăng nhập với thông báo lỗi
        }

    }
    @GetMapping("/search_user")
    public String showLoginForm(Model model) {
        return "user/Find_Account";
    }
    @GetMapping("/Forget_Password")
    public String pageForgetPassword( @ModelAttribute("user") User user, Model model)  {
        model.addAttribute("user", user);
        return "user/Forget_Password";
    }
    @GetMapping("/success")
    public String pageResetPassword( RedirectAttributes redirectAttributes, @RequestParam("email") String email)  {
        userService.updatePassword(email);
        redirectAttributes.addFlashAttribute("alert", "Mật khẩu đã được tạo, vui lòng nhập mật khẩu đã cung cấp tại gmail!");
        return "redirect:/users/login_user";
    }
    @PostMapping("/Search_order")
    public String searchUserOder(@RequestParam("ladingCode") String ladingCode, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("ladingCode", ladingCode);
        return "redirect:/users_handle/Handle_search_order";
    }

    @GetMapping("/Handle_search_order")
    public String handleSearchUserOrder(@RequestParam("ladingCode") String ladingCode, Model model) {
        ParcelRouteModel parcelRouteModel = userService.print(ladingCode);
        System.out.println(parcelRouteModel.getRouteDetails());
        model.addAttribute("parcelRoute", parcelRouteModel);
        return "user/test";
    }
}
