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
@Table(name = "post_offices")
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer officeId;

    @Column(name = "address", columnDefinition = "NVARCHAR(255) NULL")
    private String address;

    private String phone;

    @OneToMany(mappedBy = "startOffice")
    private List<Parcel> parcelsAsStart;

    @OneToMany(mappedBy = "destinationOffice")
    private List<Parcel> parcelsAsDestination;

    @OneToMany(mappedBy = "postOffice")
    private List<RouteHistory> routeHistories;

    // Getters and Setters
}
