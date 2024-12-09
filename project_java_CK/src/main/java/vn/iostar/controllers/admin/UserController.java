package vn.iostar.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iostar.service.IUserService;
import vn.iostar.entity.*;
import vn.iostar.repository.UserRepositoryAdmin;
@Controller
@RequestMapping("admin/management/userManagement")
public class UserController {
	@Autowired
	IUserService userService;
	/*
	 * @RequestMapping("") public String listUsers(ModelMap model) { List<User>
	 * listUser = userService.findAll(); model.addAttribute("listUser", listUser);
	 * return "admin/management/userManagement/listUser"; }
	 */
	
	@Autowired
	private UserRepositoryAdmin userRepository;  // Đảm bảo tên chính xác là UserRepository

	@RequestMapping("")
    public String listUsers(ModelMap model) {
        // Đếm số lượng người dùng trong cơ sở dữ liệu
        long userCount = userRepository.count();  
        
        // In ra log số lượng người dùng
        System.out.println("Total Users: " + userCount);  // In số lượng ra console/log

        // Thêm số lượng người dùng vào model để truyền đến view
        model.addAttribute("userCount", userCount);

        // Lấy danh sách người dùng
        List<User> user = userService.findAll();
        model.addAttribute("listUser", user);

        return "admin/management/userManagement/listUser";  // Trả về view
    }
}
