package vn.iostar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.embeddedld.ShippingFeeId;
import vn.iostar.entity.ShippingFee;
import vn.iostar.repository.ShippingFeeRepository;
import vn.iostar.service.IShippingFeeService;

@Service
public class ShippingFeeServiceImpl implements IShippingFeeService{

	@Autowired
    private ShippingFeeRepository shippingFeeRepository;

    @Override
	public List<ShippingFee> getAllShippingFees() {
        return shippingFeeRepository.findAll();
    }

	@Override
	public <S extends ShippingFee> S save(S entity) {
		return shippingFeeRepository.save(entity);
	}

	@Override
	public long count() {
		return shippingFeeRepository.count();
	}

	@Override
	public void deleteById(ShippingFeeId id) {
		shippingFeeRepository.deleteById(id);
	}

	@Override
	public void delete(ShippingFee entity) {
		shippingFeeRepository.delete(entity);
	}
    
	@Override
    public boolean existsById(ShippingFeeId id) {
        return shippingFeeRepository.existsById(id);
    }

	@Override
	public Optional<ShippingFee> findById(ShippingFeeId id) {
		return shippingFeeRepository.findById(id);
	}
	
	@Override
    public void update(ShippingFee shippingFee) {
        if (shippingFee != null && shippingFee.getId() != null) {
            // Check if the ShippingFee exists in the database
            if (shippingFeeRepository.existsById(shippingFee.getId())) {
                // Save the updated ShippingFee entity to the database
                shippingFeeRepository.save(shippingFee);
            } else {
                throw new IllegalArgumentException("ShippingFee not found for update");
            }
        } else {
            throw new IllegalArgumentException("Invalid ShippingFee or ShippingFee ID");
        }
    }
}
