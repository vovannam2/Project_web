package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import vn.iostar.entity.Product;

public interface IProductService {

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	<S extends Product> S save(S entity);

}
