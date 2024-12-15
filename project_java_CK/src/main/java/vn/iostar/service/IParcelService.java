package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Parcel;
import vn.iostar.models.ParcelDTO;

public interface IParcelService {

	ParcelDTO getParcelById(Integer id);

	List<ParcelDTO> findAll();

	List<Parcel> getUnassignedParcels();  // Lấy danh sách Parcel chưa phân công
	
    void assignShipperToParcel(Integer parcelId, Integer shipperId);  // Gán Shipper cho Parcel

	List<String> getAllStatuses();

	void updateParcelStatus(Integer id, String status);

	List<ParcelDTO> findByStartOfficeAddress(String address);

	List<ParcelDTO> findParcelsByStartOfficeAddress(String address);

	
}
