package vn.iostar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.iostar.entity.PostOffice;


public interface IPostOfficeService {

	List<PostOffice> getAllPostOffices();

	List<String> getAllAddresses();

	List<String> getAllOptionAddress();

	

}
package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iostar.entity.PostOffice;

public interface IPostOfficeService {
	
	void deleteById(Integer id);

	long count();

	Optional<PostOffice> findById(Integer id);

	List<PostOffice> findAllById(Iterable<Integer> ids);

	List<PostOffice> findAll();

	<S extends PostOffice> S save(S entity);
	
	Page<PostOffice> getAll(Integer pageNo);

}
