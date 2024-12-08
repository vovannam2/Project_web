package vn.iostar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.User;
import vn.iostar.repository.UserRepository;

@Service
public class UserService implements IUserService{
	@Autowired
	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public long countNewCustomerDay() {
		return userRepository.countNewCustomerDay();
	}

	@Override
	public long countNewCustomerMonth() {
		return userRepository.countNewCustomerMonth();
	}

	@Override
	public long countNewCustomerYear() {
		return userRepository.countNewCustomerYear();
	}

	@Override
	public long countTotalCustomer() {
		return userRepository.countTotalCustomer();
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<Long> getMonthlyTotal() {
		return userRepository.getMonthlyTotal();
	}

	@Override
	public List<Long> getQuarterlyTotal() {
		return userRepository.getQuarterlyTotal();
	}

}
