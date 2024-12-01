package vn.iostar.embeddedld;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ShippingFeeId implements Serializable {
    private Integer shippingTypeId;
    private Integer parcelTypeId;
}
