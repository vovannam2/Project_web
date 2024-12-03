package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String rePassword;

    @Column(nullable = false)
    private String fullName;
    private String phone;
    private String images;
    private String address;
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Parcel> createdParcels;

    @OneToMany(mappedBy = "shipper")
    private List<Parcel> shippedParcels;
    @OneToMany(mappedBy = "driver")
    private List<RouteHistory> routeHistories;

}
