package vn.iostar.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "parcels")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parcelId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "start_office_id")
    private PostOffice startOffice;

    @ManyToOne
    @JoinColumn(name = "destination_office_id")
    private PostOffice destinationOffice;

    @ManyToOne
    @JoinColumn(name = "shipper_id", nullable = true)
    private User shipper;

    private Float weight;

    private String status;

    private String note;

    @ManyToOne
    @JoinColumn(name = "shipping_type_id")
    private ShippingType shippingType;

    private LocalDateTime createDate;

    private LocalDateTime completeDate;

    @OneToMany(mappedBy = "parcel")
    private List<ParcelDetail> parcelDetails;

    @OneToMany(mappedBy = "parcel")
    private List<RouteHistory> routeHistories;

    // Getters and Setters
}
