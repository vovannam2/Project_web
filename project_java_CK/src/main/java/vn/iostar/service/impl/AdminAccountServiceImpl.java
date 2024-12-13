package vn.iostar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iostar.repository.*;
import vn.iostar.entity.User;
import vn.iostar.service.*;

@Service
public class AdminAccountServiceImpl implements IAdminAccountService{


	@Autowired
	AdminAccountRepository adminAccountRepository;

	public AdminAccountServiceImpl(AdminAccountRepository userRepository) {
		this.adminAccountRepository = userRepository;
	}

	@Override
	public <S extends User> S save(S entity) {
		return adminAccountRepository.save(entity);
	}

	@Override
	public List<User> findAll(Sort sort) {
		return adminAccountRepository.findAll(sort);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return adminAccountRepository.findAll(pageable);
	}

	@Override
	public List<User> findAll() {
		return adminAccountRepository.findAll();
	}

	@Override
	public Optional<User> findById(Integer id) {
		return adminAccountRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		adminAccountRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		adminAccountRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		adminAccountRepository.deleteAll();
	}

	@Override
	public List<User> findAllAdmin(Integer roleId) {
		return adminAccountRepository.findByRole_RoleId(1); // Tìm người dùng với roleId là 1
	}
}
