package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iostar.entity.PaymentMethod;
import vn.iostar.entity.PostOffice;

public interface IPaymentMethodService {
	
	void deleteById(Integer id);

	long count();

	Optional<PaymentMethod> findById(Integer id);

	List<PaymentMethod> findAllById(Iterable<Integer> ids);

	List<PaymentMethod> findAll();

	<S extends PaymentMethod> S save(S entity);
	
	Page<PaymentMethod> getAll(Integer pageNo);

}