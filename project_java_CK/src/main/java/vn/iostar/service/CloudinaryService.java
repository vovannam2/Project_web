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

    public String uploadImage(String imagePath) {
        try {
            Map uploadResult =cloudinary.uploader().upload(imagePath, ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
