package vn.iostar.controllers.shipper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.Parcel;
import vn.iostar.entity.User;
import vn.iostar.service.shipper.IShipperService;

@Controller
@RequestMapping("shipper")
public class ShipperController {
    @Autowired
    IShipperService shiperService;

    @GetMapping("/home")
    public String index(HttpSession session, Model model) {
        // Lấy đối tượng User từ session
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (currentUser == null) {
            return "redirect:/security/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }

        // Lấy shipperId từ entity User
        Integer shipperId = currentUser.getUserId();
        System.out.println("Shipper ID: " + shipperId); 
        
        // Lấy danh sách các đơn hàng dựa trên shipperId
         //List<Parcel> parcels = shiperService.findParcelsWithDetailsByShipperId(shipperId);
        
         Optional<Parcel> paOtional = shiperService.findById(2);
         if (paOtional.isPresent()) {
        	 Parcel donHang = paOtional.get();
             System.out.println(donHang.getShipper());  // In danh sách các đơn hàng ra console
         }
        	
//
//        // Kiểm tra nếu không có đơn hàng
//        if (parcels == null || parcels.isEmpty()) {
//            model.addAttribute("message", "No parcels found.");
//        } else {
//            model.addAttribute("parcels", parcels); // Truyền danh sách đơn hàng ra view
//        }

        return "shipper/home-shipper"; // Trả về view cho shipper
    }
}