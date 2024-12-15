package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.iostar.embeddedld.ShippingFeeId;
import vn.iostar.entity.ShippingFee;

@Service
public interface IShippingFeeService {

	List<ShippingFee> getAllShippingFees();

	void delete(ShippingFee entity);

	void deleteById(ShippingFeeId id);

	long count();

	<S extends ShippingFee> S save(S entity);

	boolean existsById(ShippingFeeId id);

	Optional<ShippingFee> findById(ShippingFeeId id);

	void update(ShippingFee shippingFee);
}
