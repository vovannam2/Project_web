package vn.iostar.controllers.admin;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iostar.entity.Parcel;
import vn.iostar.model.ParcelModel;
import vn.iostar.service.IParcelService;

@Controller
@RequestMapping("admin/parcels")
public class ParcelControllers {
    
    @Autowired(required = true)
    IParcelService parcelService;

    // Hiển thị danh sách vận đơn với phân trang
    @RequestMapping("")
    public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Parcel> list = parcelService.getAll(pageNo);
        model.addAttribute("parcels", list);
        model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
        model.addAttribute("currentPage", pageNo);
        if (list.isEmpty()) {
            model.addAttribute("message", "Không có vận đơn nào để hiển thị!");
        }
        return "admin/parcels/list";
    }

    // Thêm vận đơn mới
    @RequestMapping("/add")
    public String add(ModelMap model) {
        ParcelModel parcelModel = new ParcelModel();
        parcelModel.setIsEdit(false);
        model.addAttribute("parcel", parcelModel);
        return "admin/parcels/addOrEdit";
    }

    // Sửa vận đơn
    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Integer parcelId) {
        Optional<Parcel> parcel = parcelService.findById(parcelId);
        ParcelModel parcelModel = new ParcelModel();
        if (parcel.isPresent()) {
            Parcel entity = parcel.get();
            BeanUtils.copyProperties(entity, parcelModel);
            parcelModel.setIsEdit(true);
            model.addAttribute("parcel", parcelModel);
            return new ModelAndView("admin/parcels/addOrEdit", model);
        }
        model.addAttribute("message", "Vận đơn không tồn tại!");
        return new ModelAndView("forward:/admin/parcels", model);
    }

    // Lưu hoặc cập nhật vận đơn
    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("parcel") ParcelModel parcelModel,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/parcels/addOrEdit");
        }
        
        Parcel entity = new Parcel();
        // Sao chép từ Model sang Entity
        BeanUtils.copyProperties(parcelModel, entity);
        
        // Lưu hoặc cập nhật vận đơn
        parcelService.save(entity);
        
        // Thông báo sau khi lưu
        String message = "";
        if (parcelModel.getIsEdit() == true) {
            message = "Chỉnh sửa vận đơn thành công!";
        } else {
            message = "Thêm vận đơn mới thành công!";
        }
        model.addAttribute("message", message);
        
        // Chuyển hướng về danh sách vận đơn
        return new ModelAndView("forward:/admin/parcels", model);
    }

    // Xóa vận đơn
    @GetMapping("delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable("id") Integer parcelId) {
        try {
            parcelService.deleteById(parcelId);
            model.addAttribute("message", "Xóa vận đơn thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Không thể xóa vì vận đơn có liên quan đến các bản ghi khác!");
        }
        return new ModelAndView("forward:/admin/parcels", model);
    }
}
