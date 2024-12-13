package vn.iostar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iostar.repository.*;
import vn.iostar.entity.ParcelType;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;
import vn.iostar.service.*;

@Service
public class RoleServiceImpl implements IRoleService{


	@Autowired
	RoleRepository roleRepository;

	@Override
	public long count() {
		return roleRepository.count();
	}

	@Override
	public <S extends Role> S save(S entity) {
		return roleRepository.save(entity);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}

	@Override
	public void delete(Role entity) {
		roleRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		roleRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll() {
		roleRepository.deleteAll();
	}

}
