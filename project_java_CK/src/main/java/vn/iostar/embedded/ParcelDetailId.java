package vn.iostar.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class ParcelDetailId implements Serializable {
    private Integer parcelId;
    private Integer productId;
}
