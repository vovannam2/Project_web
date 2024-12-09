package vn.iostar.repository;

import org.springframework.stereotype.Repository;

import vn.iostar.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepositoryAdmin extends JpaRepository<User, Integer>{

	long count();  // Phương thức để đếm số lượng bản ghi

}
