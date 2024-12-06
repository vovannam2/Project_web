package vn.iostar.controllers.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iostar.entity.User;
import vn.iostar.service.User.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
    public String save( @ModelAttribute(name = "User") User user,
                        @RequestParam("images") MultipartFile multipartFile,
                        RedirectAttributes redirectAttributes
                        ) throws IOException {
        //create file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(fileName);
        user.setImagePath(fileName);
        System.out.println((user.getAvatarImagePath()));
        User saveUser = userService.create(user);
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

        redirectAttributes.addFlashAttribute("alert", "Đã đăng kí thành công, vui lòng nhập mật khẩu đã cung cấp tại gmail!");
        return "redirect:/users/login_user";
    }
    @GetMapping("/login_user")
    public String showLoginForm(Model model) {
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
            return "redirect:/users/login_user"; // Quay lại form đăng nhập với thông báo lỗi
        }
    }

}
