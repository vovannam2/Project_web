package vn.iostar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByEmail(String email);
	User findByEmail(String email);
	Optional<User> findByUsername (String username);
}