package vn.iostar.service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.User;

@Repository
public interface IUserService {
    public void create(User user);
}
