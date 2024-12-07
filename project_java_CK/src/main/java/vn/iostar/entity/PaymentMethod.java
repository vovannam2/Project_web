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
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(500)")
    private String name;

    private Boolean status;
    
    @Column(name = "description", columnDefinition = "NVARCHAR(500) NULL")
    private String description;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Parcel> parcels;

    // Getters and Setters
}
