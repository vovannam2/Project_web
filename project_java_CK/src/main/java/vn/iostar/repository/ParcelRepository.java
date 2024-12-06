package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer>{
	
	@Query("SELECT COUNT(p) FROM Parcel p WHERE FUNCTION('DATE', p.createDate) = CURRENT_DATE")
	Long countParcelsCreatedToday();

}
