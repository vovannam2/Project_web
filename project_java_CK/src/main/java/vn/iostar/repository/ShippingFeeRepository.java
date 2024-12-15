package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.ShippingFee;
import vn.iostar.embeddedld.ShippingFeeId;

public interface ShippingFeeRepository extends JpaRepository<ShippingFee, ShippingFeeId> {
    
	boolean existsById(ShippingFeeId id); 
}
