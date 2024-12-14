package vn.iostar.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParcelDTO {
    private Integer parcelId;
    private String userName;
    private String recipientName;
    private String shipperName;
    private Float weight;
    private String status;
    private String note;
    private String shippingType;
    private LocalDateTime createDate;
    private LocalDateTime completeDate;
}
