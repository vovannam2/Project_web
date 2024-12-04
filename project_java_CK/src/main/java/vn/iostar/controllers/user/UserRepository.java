package vn.iostar.controllers.user;


import org.springframework.data.jpa.repository.JpaRepository;

import vn.iostar.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	boolean existsByEmail(String email);
    User findByEmail(String email);
    
}
