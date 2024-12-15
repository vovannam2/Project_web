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
import vn.iostar.entity.User;
import vn.iostar.service.*;

@Service
public class ParcelTypeServiceImpl implements IParcelTypeService{


	@Autowired
	ParcelTypeRepository parcelTypeRepository;

	public ParcelTypeServiceImpl(ParcelTypeRepository parcelTypeRepository) {
		this.parcelTypeRepository = parcelTypeRepository;
	}

	@Override
	public long count() {
		return parcelTypeRepository.count();
	}

	@Override
	public <S extends ParcelType> S save(S entity) {
		return parcelTypeRepository.save(entity);
	}

	@Override
	public List<ParcelType> findAll(Sort sort) {
		return parcelTypeRepository.findAll(sort);
	}

	@Override
	public Page<ParcelType> findAll(Pageable pageable) {
		return parcelTypeRepository.findAll(pageable);
	}

	@Override
	public List<ParcelType> findAll() {
		return parcelTypeRepository.findAll();
	}

	@Override
	public Optional<ParcelType> findById(Integer id) {
		return parcelTypeRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		parcelTypeRepository.deleteById(id);
	}

	@Override
	public void delete(ParcelType entity) {
		parcelTypeRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		parcelTypeRepository.deleteAll();
	}
	
	
}
