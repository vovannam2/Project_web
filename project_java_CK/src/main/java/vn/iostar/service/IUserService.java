package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.User;

public interface IUserService {

	void deleteUserById(Integer id);

	void saveUser(User user);

	List<User> findAll();

	User findById(Integer id);

	List<User> findShippers();

	

}
