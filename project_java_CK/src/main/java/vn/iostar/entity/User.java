package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
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

    private String username;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullname;

    private String phone;

    private String images;
    
    @JoinColumn(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private String address;

    @Column(nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "user")
    private List<Parcel> createdParcels;

    @OneToMany(mappedBy = "shipper")
    private List<Parcel> shippedParcels;

    @OneToMany(mappedBy = "driver")
    private List<RouteHistory> routeHistories;
}