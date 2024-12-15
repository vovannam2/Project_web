package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.PaymentMethod;

@Repository
public interface PaymethodRepository extends JpaRepository<PaymentMethod, String> {
}