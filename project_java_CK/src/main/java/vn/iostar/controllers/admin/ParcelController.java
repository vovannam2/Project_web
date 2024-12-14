package vn.iostar.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iostar.entity.Parcel;
import vn.iostar.models.ParcelDTO;
import vn.iostar.service.IParcelService;

@Controller
@RequestMapping("admin/management/AdminAccountManagement/parcels")
public class ParcelController {
//http://localhost:8181/admin/management/AdminAccountManagement/parcels
    @Autowired
    private IParcelService parcelService;  // Inject service theo interface
    
    @GetMapping("")
    public String getAllParcels(Model model) {
        // Lấy tất cả các Parcel từ service
        List<Parcel> parcels = parcelService.findAll();

        // Thêm danh sách Parcel vào model
        model.addAttribute("parcels", parcels);

        return "admin/management/ParcelManagement/listParcel";  // Tên template Thymeleaf để hiển thị danh sách Parcel
    }
    @GetMapping("parcelDetail/{id}")
    public String getParcelDetails(@PathVariable Integer id, Model model) {
        // Lấy ParcelDTO từ service
        ParcelDTO parcelDTO = parcelService.getParcelById(id);
        
        // Nếu không tìm thấy ParcelDTO, trả về trang lỗi (404)
        if (parcelDTO == null) {
            return "error/404";
        }

        // Thêm ParcelDTO vào model để truyền đến view
        model.addAttribute("parcel", parcelDTO);

        return "admin/management/ParcelManagement/parcelDetail";  // Tên template Thymeleaf
    }
}
