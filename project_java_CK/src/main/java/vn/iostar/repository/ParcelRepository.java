package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.Parcel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer> {
    @Query("SELECT p FROM Parcel p WHERE p.user.userId = :userId AND p.status != 'DELIVERED'")
    List<Parcel> findUndeliveredParcelsByUserId(@Param("userId") Integer userId);

    Parcel findByParcelId(int id);
}
