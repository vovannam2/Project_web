package vn.iostar.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import vn.iostar.config.CloudinaryConfig;
import vn.iostar.entity.Product;
import vn.iostar.service.CloudinaryService;
import vn.iostar.service.IProductService;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private CloudinaryService cloudinaryService; // Inject CloudinaryService
	@Autowired
	IProductService productService;

	@GetMapping("/add")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "manager/add_product";
	}

	@PostMapping("/add")
	public String saveProduct(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("weight") Float weight, @RequestParam("image") MultipartFile image, Model model) {
		try {
			// Lưu ảnh lên Cloudinary và lấy URL
			String imageUrl = cloudinaryService.uploadImage(image.getBytes());

			if (imageUrl == null) {
				model.addAttribute("error", "Không thể tải lên ảnh. Vui lòng thử lại.");
				return "add_product";
			}

			// Tạo và lưu sản phẩm mới
			Product product = Product.builder().name(name).description(description).weight(weight).image(imageUrl).build();
			// Giả sử bạn có service lưu sản phẩm
			productService.save(product);

			return "redirect:/products/view/" + product.getProductId();
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "Lỗi xử lý ảnh. Vui lòng thử lại.");
			return "manager/add_product";
		}
	}

	@GetMapping("/view/{id}")
	public String viewProduct(@PathVariable("id") Integer id, Model model) {
		// Lấy sản phẩm từ database (giả sử bạn có service này)
		Optional<Product> product = productService.findById(id);

		// Kiểm tra nếu sản phẩm không tồn tại
		if (product.isEmpty()) {
			model.addAttribute("error", "Không tìm thấy sản phẩm!");
			return "error_page";
		}

		// Thêm sản phẩm vào model và chuyển đến trang view
		model.addAttribute("product", product.get());
		return "manager/view_product";
	}

}
