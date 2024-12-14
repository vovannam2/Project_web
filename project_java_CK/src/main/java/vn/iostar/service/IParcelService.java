package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Parcel;
import vn.iostar.models.ParcelDTO;

public interface IParcelService {

	ParcelDTO getParcelById(Integer id);

	List<Parcel> findAll();

	List<Parcel> getUnassignedParcels();  // Lấy danh sách Parcel chưa phân công
	
    void assignShipperToParcel(Integer parcelId, Integer shipperId);  // Gán Shipper cho Parcel

}
