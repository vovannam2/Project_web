package vn.iostar.service;

import org.springframework.stereotype.Repository;
import vn.iostar.entity.User;

@Repository
public interface IUserService {
    public void create(User user);
}
