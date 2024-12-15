package vn.iostar.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.entity.Parcel;
import vn.iostar.entity.PostOffice;
import vn.iostar.entity.User;
import vn.iostar.models.ParcelDTO;
import vn.iostar.repository.ParcelRepository;
import vn.iostar.repository.PostOfficeRepository;
import vn.iostar.repository.UserRepositoryAdmin;
import vn.iostar.service.IParcelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParcelServiceImpl implements IParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    @Override
    public ParcelDTO getParcelById(Integer id) {
        Optional<Parcel> parcelOptional = parcelRepository.findById(id);
        
        if (parcelOptional.isPresent()) {
            Parcel parcel = parcelOptional.get();
            ParcelDTO parcelDTO = new ParcelDTO();
            parcelDTO.setParcelId(parcel.getParcelId());
            parcelDTO.setUserId(parcel.getUser().getUserId());
            parcelDTO.setUserName(parcel.getUser().getFullname());
            parcelDTO.setRecipientName(parcel.getRecipient() != null ? parcel.getRecipient().getFullname() : "N/A");
            parcelDTO.setShipperName(parcel.getShipper() != null ? parcel.getShipper().getFullname() : "N/A");
            parcelDTO.setWeight(parcel.getWeight());
            parcelDTO.setStatus(parcel.getStatus());
            parcelDTO.setNote(parcel.getNote());
            parcelDTO.setShippingType(parcel.getShippingType() != null ? parcel.getShippingType().getName() : "N/A");
            parcelDTO.setCreateDate(parcel.getCreateDate());
            parcelDTO.setCompleteDate(parcel.getCompleteDate());
            parcelDTO.setStartOffice(parcel.getStartOffice() != null ? parcel.getStartOffice().getAddress() : "N/A");
            parcelDTO.setDestinationOffice(parcel.getDestinationOffice() != null ? parcel.getDestinationOffice().getAddress() : "N/A");
            parcelDTO.setPaymentMethod(parcel.getPaymentMethod() != null ? parcel.getPaymentMethod().getName() : "N/A");
            parcelDTO.setRecipientId(parcel.getRecipient() != null ? parcel.getRecipient().getRecipientId() : null); // Gán null nếu không có recipient
            return parcelDTO;
        } else {
            return null;  // Trả về null nếu không tìm thấy Parcel
        }
    }


	@Override
	public List<ParcelDTO> findAll() {
	    List<Parcel> parcels = parcelRepository.findAll();
	    List<ParcelDTO> parcelDTOs = new ArrayList<>();

	    for (Parcel parcel : parcels) {
	        ParcelDTO parcelDTO = new ParcelDTO();
	        parcelDTO.setParcelId(parcel.getParcelId());
	        parcelDTO.setUserId(parcel.getUser().getUserId());
	        parcelDTO.setUserName(parcel.getUser().getFullname());
	        parcelDTO.setRecipientName(parcel.getRecipient() != null ? parcel.getRecipient().getFullname() : "N/A");
	        parcelDTO.setShipperName(parcel.getShipper() != null ? parcel.getShipper().getFullname() : "N/A");
	        parcelDTO.setWeight(parcel.getWeight());
	        parcelDTO.setStatus(parcel.getStatus());
	        parcelDTO.setNote(parcel.getNote());
	        parcelDTO.setShippingType(parcel.getShippingType() != null ? parcel.getShippingType().getName() : "N/A");
	        parcelDTO.setCreateDate(parcel.getCreateDate());
	        parcelDTO.setCompleteDate(parcel.getCompleteDate());
	        parcelDTO.setStartOffice(parcel.getStartOffice() != null ? parcel.getStartOffice().getAddress() : "N/A");
            parcelDTO.setDestinationOffice(parcel.getDestinationOffice() != null ? parcel.getDestinationOffice().getAddress() : "N/A");
	        parcelDTO.setPaymentMethod(parcel.getPaymentMethod() != null ? parcel.getPaymentMethod().getName() : "N/A");
	        parcelDTO.setRecipientId(parcel.getRecipient() != null ? parcel.getRecipient().getRecipientId() : null);
	        
	        parcelDTOs.add(parcelDTO);
	    }

	    return parcelDTOs;
	}
	@Autowired
    private UserRepositoryAdmin userRepository;  // Repository của User để lấy danh sách shipper

    @Override
    public List<Parcel> getUnassignedParcels() {
        return parcelRepository.findByShipperIsNull();
    }

    @Override
    public void assignShipperToParcel(Integer parcelId, Integer shipperId) {
        // Tìm Parcel và Shipper
        Parcel parcel = parcelRepository.findById(parcelId).orElseThrow(() -> new IllegalArgumentException("Parcel not found!"));
        User shipper = userRepository.findById(shipperId).orElseThrow(() -> new IllegalArgumentException("Shipper not found!"));

        // Gán Shipper cho Parcel
        parcel.setShipper(shipper);
        parcelRepository.save(parcel);
    }
    
//    @Override
//    public List<String> getAllStatuses() {
//        // Gọi phương thức từ repository để lấy tất cả trạng thái duy nhất
//        return parcelRepository.findAllDistinctStatuses();
//    }
    
    @Override
    public void updateParcelStatus(Integer id, String status) {
        Parcel parcel = parcelRepository.findById(id).orElseThrow(() -> new RuntimeException("Parcel not found"));
        parcel.setStatus(status);
        parcelRepository.save(parcel);
    }

    
    @Override
	public List<String> getAllStatuses() {
        return List.of("Đang giao", "Đã giao", "Đã hủy", "Đang xử lý");
    }
    
    
    @Override
    public List<ParcelDTO> findByStartOfficeAddress(String address) {
        List<Parcel> parcels = parcelRepository.findByStartOffice_Address(address);
        List<ParcelDTO> parcelDTOs = new ArrayList<>();

        for (Parcel parcel : parcels) {
            ParcelDTO parcelDTO = new ParcelDTO();
            parcelDTO.setParcelId(parcel.getParcelId());
            parcelDTO.setUserId(parcel.getUser().getUserId());
            parcelDTO.setUserName(parcel.getUser().getFullname());
            parcelDTO.setRecipientName(parcel.getRecipient() != null ? parcel.getRecipient().getFullname() : "N/A");
            parcelDTO.setShipperName(parcel.getShipper() != null ? parcel.getShipper().getFullname() : "N/A");
            parcelDTO.setWeight(parcel.getWeight());
            parcelDTO.setStatus(parcel.getStatus());
            parcelDTO.setNote(parcel.getNote());
            parcelDTO.setShippingType(parcel.getShippingType() != null ? parcel.getShippingType().getName() : "N/A");
            parcelDTO.setCreateDate(parcel.getCreateDate());
            parcelDTO.setCompleteDate(parcel.getCompleteDate());
            parcelDTO.setStartOffice(parcel.getStartOffice() != null ? parcel.getStartOffice().getAddress() : "N/A");
            parcelDTO.setDestinationOffice(parcel.getDestinationOffice() != null ? parcel.getDestinationOffice().getAddress() : "N/A");
            parcelDTOs.add(parcelDTO);
        }

        return parcelDTOs;
    }
    

//  @Override
//  public List<String> getAllStatuses() {
//      // Gọi phương thức từ repository để lấy tất cả trạng thái duy nhất
//      return parcelRepository.findAllDistinctStatuses();
//  }
    
    @Autowired
    private PostOfficeRepository postOfficeRepository;
    public List<ParcelDTO> findParcelsByStartOfficeAddress(String address) {
        PostOffice postOffice = postOfficeRepository.findByAddress(address);
        if (postOffice != null) {
            return parcelRepository.findByStartOffice(postOffice);
        }
        return new ArrayList<>(); // Trả về danh sách rỗng nếu không tìm thấy
    }
    
}
