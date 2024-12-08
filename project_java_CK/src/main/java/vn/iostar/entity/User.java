package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
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

    @Transient
    private MultipartFile images;

    private String imagePath;

    private String address;
    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Parcel> createdParcels;

    @OneToMany(mappedBy = "shipper")
    private List<Parcel> shippedParcels;

    @OneToMany(mappedBy = "driver")
    private List<RouteHistory> routeHistories;

    @Transient
    public String getAvatarImagePath(){
        if(imagePath == null || userId == null){
            return null;
        }
        return "/update-avatar/" + userId +"/"+ imagePath;
    }

}
