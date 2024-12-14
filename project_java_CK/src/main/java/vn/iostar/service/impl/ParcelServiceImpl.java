package vn.iostar.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.entity.Parcel;
import vn.iostar.entity.User;
import vn.iostar.models.ParcelDTO;
import vn.iostar.repository.ParcelRepository;
import vn.iostar.repository.UserRepositoryAdmin;
import vn.iostar.service.IParcelService;

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
            parcelDTO.setUserName(parcel.getUser().getFullname());
            parcelDTO.setRecipientName(parcel.getRecipient() != null ? parcel.getRecipient().getFullname() : "N/A");
            parcelDTO.setShipperName(parcel.getShipper() != null ? parcel.getShipper().getFullname() : "N/A");
            parcelDTO.setWeight(parcel.getWeight());
            parcelDTO.setStatus(parcel.getStatus());
            parcelDTO.setNote(parcel.getNote());
            parcelDTO.setShippingType(parcel.getShippingType() != null ? parcel.getShippingType().getName() : "N/A");
            parcelDTO.setCreateDate(parcel.getCreateDate());
            parcelDTO.setCompleteDate(parcel.getCompleteDate());

            return parcelDTO;
        } else {
            return null;  // Trả về null nếu không tìm thấy Parcel
        }
    }

	@Override
	public List<Parcel> findAll() {
		return parcelRepository.findAll();
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
}
