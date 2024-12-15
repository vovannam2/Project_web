package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.User;

import java.util.List;
import java.util.Map;

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
    @Query("""
    SELECT 
        parcel.parcelId AS parcelId, 
        pr.name AS productName, 
        pr.image AS productImage, 
        pr.weight AS productWeight, 
        parcel.createDate AS createDate, 
        parcel.completeDate AS completeDate, 
        pd.quantity AS quantity, 
        pr.description AS productDescription, 
        sf.fee AS shippingFee, pr.money AS money,
        (pd.quantity * pr.money) + sf.fee AS totalAmount, 
        pm.status AS paymentStatus
    FROM User u
    JOIN u.createdParcels parcel
    JOIN parcel.parcelDetails pd
    JOIN pd.product pr
    JOIN parcel.shippingType st
    JOIN st.shippingFees sf
    JOIN parcel.paymentMethod pm
    WHERE u.userId = :userId 
      AND pm.status = false
    """)
    List<Map<String, Object>> findUnpaidParcelsByUserId(@Param("userId") Integer userId);
}

