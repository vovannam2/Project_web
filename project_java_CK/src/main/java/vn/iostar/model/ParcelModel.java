package vn.iostar.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.iostar.entity.ParcelDetail;
import vn.iostar.entity.PaymentMethod;
import vn.iostar.entity.PostOffice;
import vn.iostar.entity.Recipient;
import vn.iostar.entity.RouteHistory;
import vn.iostar.entity.User;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParcelModel {
	
    private Integer parcelId;
    // mã vận đơn: -> know user id:
    private String ladingCode;

    private User user;

    private Recipient recipient;

    private PaymentMethod paymentMethod;

    private PostOffice startOffice;

    private PostOffice destinationOffice;
    private String name;
    private String image;
    private String description;
    
    private User shipper;

    private Float weight;

    private String status;

    private String note;
    
    
    private int shippingFee;


    private LocalDateTime createDate;

    private LocalDateTime completeDate;

    private List<ParcelDetail> parcelDetails;
    private List<RouteHistory> routeHistories;
    private Boolean isEdit = false;
}
