package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Product;
import vn.iostar.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	ProductRepository productRepo;

	public ProductServiceImpl(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepo.findById(id);
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepo.save(entity);
	}
	
}
