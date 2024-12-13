package vn.iostar.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iostar.entity.*;
import vn.iostar.models.ParcelTypeModel;
import vn.iostar.models.UserModel;
import vn.iostar.repository.AdminAccountRepository;
import vn.iostar.service.IAdminAccountService;
import vn.iostar.service.IRoleService;
@Controller
@RequestMapping("admin/management/AdminAccountManagement")
public class AdminAccountController {
	@Autowired
	IAdminAccountService adminAccountService;
	@Autowired
	IRoleService roleService;
	
	/*
	 * @RequestMapping("") public String listUsers(ModelMap model) { List<User>
	 * listUser = userService.findAll(); model.addAttribute("listUser", listUser);
	 * return "admin/management/userManagement/listUser"; }
	 */
	
	@Autowired
	private AdminAccountRepository adminAccountRepository;  // Đảm bảo tên chính xác là UserRepository

	@RequestMapping("")
    public String listUsers(ModelMap model) {
        // Đếm số lượng người dùng trong cơ sở dữ liệu
        long userCount = adminAccountRepository.count();  
        
        // In ra log số lượng người dùng
        System.out.println("Total Users: " + userCount);  // In số lượng ra console/log

        // Thêm số lượng người dùng vào model để truyền đến view
        model.addAttribute("userCount", userCount);

        // Lấy danh sách người dùng
        List<User> admin = adminAccountService.findAllAdmin(1);
        model.addAttribute("listAdmin", admin);

        return "admin/management/AdminAccountManagement/listAdminAccount";  // Trả về view
    }
	@GetMapping("add")
	public String Add(ModelMap model)
	{
		UserModel user = new UserModel();
		user.setIsEdit(false);
		user.setRoleId(1);
	    
		System.out.println("User: " + user);
		model.addAttribute("listAdmin", user);
		return "admin/management/AdminAccountManagement/AddOrEditAdminAccount";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, 
	                                 @Valid @ModelAttribute("listAdmin") UserModel user, 
	                                 BindingResult result, 
	                                 @RequestParam("roleId") Integer roleId) {

	    if (result.hasErrors()) {
	        List<Role> roles = roleService.findAll();
	        model.addAttribute("roles", roles);
	        return new ModelAndView("admin/management/AdminAccountManagement/AddOrEditAdminAccount");
	    }

	    User entity = new User();
	    BeanUtils.copyProperties(user, entity);

	    // Lấy đối tượng Role từ roleId, xử lý Optional để tránh NullPointerException
	    Role role = roleService.findById(roleId)
	                           .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy Role với ID: " + roleId));
	    entity.setRole(role);

	    adminAccountService.save(entity);	
	   
	    String message = user.getIsEdit() ? "Admin đã được cập nhật" : "Admin đã được thêm thành công";
	    model.addAttribute("message", message);

	    return new ModelAndView("redirect:/admin/management/AdminAccountManagement", model);
	}

	@GetMapping("edit/{userId}")
	public ModelAndView edit(ModelMap model, @PathVariable("userId") Integer userId) {
		Optional<User> opt = adminAccountService.findById(userId);
		UserModel AdminAccountModel = new UserModel();
		if(opt.isPresent()) {
			User entity = opt.get();
			BeanUtils.copyProperties(entity, AdminAccountModel);
			AdminAccountModel.setIsEdit(true);
			model.addAttribute("listAdmin", AdminAccountModel);
			return new ModelAndView("admin/management/AdminAccountManagement/AddOrEditAdminAccount", model);
		}
		model.addAttribute("message", "Account của admin này không tồn tại!");
		return new ModelAndView("admin/management/AdminAccountManagement", model);
	}
	@GetMapping("delete/{userId}")
	public ModelAndView delete(ModelMap model, @PathVariable("userId") Integer userId) {
		adminAccountService.deleteById(userId);
		model.addAttribute("message", "Account Admin đã được xóa thành công!");
		return new ModelAndView("forward:/admin/management/AdminAccountManagement", model);
	}
}
