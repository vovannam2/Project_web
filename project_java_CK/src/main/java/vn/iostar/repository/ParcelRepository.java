package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.Parcel;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer> {

	@Query(value = "SELECT p.* FROM parcels p WHERE p.shipper_id = :shipperId", nativeQuery = true)
	List<Parcel> findParcelsWithDetailsByShipperId(@Param("shipperId") Integer shipperId);
	


}
