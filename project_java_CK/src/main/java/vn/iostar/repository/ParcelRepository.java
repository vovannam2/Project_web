package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.Parcel;
import vn.iostar.model.ParcelRouteModel;


@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer> {
    @Query("SELECT p FROM Parcel p " +
            "LEFT JOIN FETCH p.routeHistories rh " +
            "LEFT JOIN FETCH rh.postOffice po " +
            "WHERE p.ladingCode = :ladingCode")
    Parcel findParcelWithDetails(@Param("ladingCode") String ladingCode);
}

