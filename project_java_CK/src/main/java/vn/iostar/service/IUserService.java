package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iostar.entity.User;

public interface IUserService {

	void deleteAll();

	void delete(User entity);

	void deleteById(Integer id);

	Optional<User> findById(Integer id);

	List<User> findAll();

	Page<User> findAll(Pageable pageable);

	List<User> findAll(Sort sort);

	<S extends User> S save(S entity);

}
