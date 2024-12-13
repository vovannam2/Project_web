package vn.iostar.repository;

import org.springframework.stereotype.Repository;

import vn.iostar.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface AdminAccountRepository extends JpaRepository<User, Integer>{

	long count();  // Phương thức để đếm số lượng bản ghi
	List<User> findByRole_RoleId(Integer roleId); // Sử dụng Role_RoleId
}
