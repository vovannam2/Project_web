package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.User;

public interface IUserService {
	
    long countNewCustomerDay();
	
	long countNewCustomerMonth();
	
	long countNewCustomerYear();
	
	long countTotalCustomer();
	
	boolean existsByEmail(String email);
	
    User findByEmail(String email);
    
    List<Long> getMonthlyTotal();
    
    List<Long> getQuarterlyTotal();
}
