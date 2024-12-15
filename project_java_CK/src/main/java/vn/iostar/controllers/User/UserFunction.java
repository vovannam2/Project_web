package vn.iostar.controllers.User;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
@RequestMapping("/account_handle")
public class UserFunction {
    @Autowired
    UserFunctionServiceImpl userService;

    @PostMapping("/search")
    public String forgetPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes ){
        User resultUser = userService.checkEmailExist(email);
        if(resultUser != null){
            redirectAttributes.addFlashAttribute("user", resultUser);
            return "redirect:/account_handle/Forget_Password";
        }else {
            redirectAttributes.addFlashAttribute("alert", "Email chưa đăng kí!");
            return "redirect:/account_handle/search_user"; // Quay lại form đăng nhập với thông báo lỗi
        }
    }
    @GetMapping("/search_user")
    public String showLoginForm(Model model) {
        return "security/Find_Account";
    }
    @GetMapping("/Forget_Password")
    public String pageForgetPassword( @ModelAttribute("user") User user, Model model)  {
        model.addAttribute("user", user);
        return "security/Forget_password";
    }
    @GetMapping("/success")
    public String pageResetPassword( RedirectAttributes redirectAttributes, @RequestParam("email") String email)  {
        userService.updatePassword(email);
        redirectAttributes.addFlashAttribute("alert", "Mật khẩu đã được tạo, vui lòng nhập mật khẩu đã cung cấp tại gmail!");
        return "redirect:/account/login_account";
    }
    @PostMapping("/Search_order")
    public String searchUserOder(@RequestParam("ladingCode") String ladingCode, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("ladingCode", ladingCode);
        return "redirect:/account_handle/Handle_search_order";
    }

    @GetMapping("/Handle_search_order")
    public String handleSearchUserOrder(@RequestParam("ladingCode") String ladingCode, Model model) {
        ParcelRouteModel parcelRouteModel = userService.print(ladingCode);
        System.out.println(parcelRouteModel.getRouteDetails());
        model.addAttribute("parcelRoute", parcelRouteModel);
        return "user/Search_ParcelRoute";
    }
    @GetMapping("/ChangePassword")
    public String layoutChangePassword( Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user1 = (User)request.getSession().getAttribute("user");
        model.addAttribute("user", user1);
        if (user1 == null) {
            redirectAttributes.addFlashAttribute("alert", "session time out");
            return "redirect:/account/login_account";  // Chuyển hướng tới trang đăng nhập hoặc hiển thị lỗi
        }
        model.addAttribute("user", user1);
        return "security/Change_Password";
    }
    @PostMapping("/change-password")
    public String handleChangePassword(@RequestParam("password") String password, HttpServletRequest request,
                                       RedirectAttributes redirectAttributes){
        User user = (User) request.getSession().getAttribute("user");


        if(userService.handleChangePassword(user.getEmail(), password)){
            redirectAttributes.addFlashAttribute("alert", "success change password");
        }else {
            redirectAttributes.addFlashAttribute("alert", "new password don't match old password");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "redirect:/account_handle/ChangePassword";
    }
    @GetMapping("/layoutEdit")
    public String layoutEditUser(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "user/infoUser";
    }
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute(name = "user") User user, @RequestParam("email") String email,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             @RequestParam("address") String address,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("images") MultipartFile multipartFile,
                             Model model) throws IOException {
        user = userService.getUser(email);

        user.setFullname(name);
        user.setPhone(phone);
        user.setAddress(address);
        System.out.println(user.getUserId());
        System.out.println(name+ phone+ address);
        System.out.println(user.getEmail());
        // Xử lý ảnh nếu có
        if(multipartFile.isEmpty()){
            userService.updateUser(user);
        }else {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setImagePath(fileName);
            User saveUser = userService.updateUser(user);
            String uploadDir = "./update-avatar/" +  saveUser.getUserId();
            // save path in dir
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
                System.out.println(filePath.toFile().getAbsolutePath());
            } catch (IOException e) {
                throw new IOException("not success save file" + fileName);
            }

        }
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("alert", "information is update success");
        return "redirect:/account_handle/layoutEdit"; // Redirect lại để hiển thị thông tin đã cập nhật
    }
}
