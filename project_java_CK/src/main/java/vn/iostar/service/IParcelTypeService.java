package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iostar.entity.ParcelType;
import vn.iostar.entity.User;

public interface IParcelTypeService {

	void deleteAll();

	void delete(ParcelType entity);

	void deleteById(Integer id);

	Optional<ParcelType> findById(Integer id);

	List<ParcelType> findAll();

	Page<ParcelType> findAll(Pageable pageable);

	List<ParcelType> findAll(Sort sort);

	<S extends ParcelType> S save(S entity);

	long count();

}
