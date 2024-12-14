package vn.iostar.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryService {

    @Autowired
    Cloudinary cloudinary;

    public String uploadImage(byte[] imageBytes) {
        try {
            // Upload dữ liệu byte[] lên Cloudinary
            Map<?, ?> uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());

            // Trích xuất URL ảnh từ kết quả upload
            return (String) uploadResult.get("url");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
