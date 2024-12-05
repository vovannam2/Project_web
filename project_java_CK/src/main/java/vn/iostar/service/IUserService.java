package vn.iostar.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import vn.iostar.entity.User;

import java.io.IOException;

@Repository
public interface IUserService {
    public void create(User user) ;
}
