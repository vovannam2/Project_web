package vn.iostar.service.user;


import vn.iostar.entity.User;

public interface IUserService {

	boolean existsById(Integer id);

	User findByEmail(String email);

	boolean existsByEmail(String email);



}
