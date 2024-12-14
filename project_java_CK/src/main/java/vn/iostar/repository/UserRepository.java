package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    @Query("SELECT p.productId, p.name, p.image, p.weight, parcel.createDate, parcel.completeDate, pd.quantity, p.description, parcel.status " +
            "FROM User u " +
            "JOIN u.createdParcels parcel " +
            "JOIN parcel.parcelDetails pd " +
            "JOIN pd.product p " +
            "WHERE u.userId = :userId")
    List<Object[]> findProductDetailsForUser(@Param("userId") Integer userId);
}

