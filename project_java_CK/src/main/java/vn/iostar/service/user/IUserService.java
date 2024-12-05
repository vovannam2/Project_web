package vn.iostar.service.user;

import java.util.Optional;

import vn.iostar.entity.User;

public interface IUserService {

	boolean existsById(Integer id);

	User findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);

}
