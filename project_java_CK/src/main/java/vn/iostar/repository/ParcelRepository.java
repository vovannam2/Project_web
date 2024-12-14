package vn.iostar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Integer> {
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh tại đây nếu cần
	// Lấy danh sách Parcel chưa có shipper
    List<Parcel> findByShipperIsNull();
}
