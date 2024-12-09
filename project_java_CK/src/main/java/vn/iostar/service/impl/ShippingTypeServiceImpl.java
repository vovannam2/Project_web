package vn.iostar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iostar.repository.*;
import vn.iostar.entity.ShippingType;
import vn.iostar.entity.User;
import vn.iostar.service.*;

@Service
public class ShippingTypeServiceImpl implements IShippingTypeService{


	@Autowired
	ShippingTypeRepository shippingTypeRepository;

	public ShippingTypeServiceImpl(ShippingTypeRepository shippingTypeRepository) {
		this.shippingTypeRepository = shippingTypeRepository;
	}
// entity

	@Override
	public <S extends ShippingType> S save(S entity) {
		return shippingTypeRepository.save(entity);
	}

	@Override
	public List<ShippingType> findAll(Sort sort) {
		return shippingTypeRepository.findAll(sort);
	}

	@Override
	public Page<ShippingType> findAll(Pageable pageable) {
		return shippingTypeRepository.findAll(pageable);
	}

	@Override
	public List<ShippingType> findAll() {
		return shippingTypeRepository.findAll();
	}

	@Override
	public Optional<ShippingType> findById(Integer id) {
		return shippingTypeRepository.findById(id);
	}

	@Override
	public long count() {
		return shippingTypeRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		shippingTypeRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		shippingTypeRepository.deleteAll();
	}
	
}