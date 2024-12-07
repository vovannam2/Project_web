package vn.iostar.service.shipper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Parcel;
import vn.iostar.repository.ParcelRepository;

@Service
public class ShipperService implements IShipperService{
	@Autowired 
	ParcelRepository parcelRepo ;

	public ShipperService(ParcelRepository parcelRepo) {
		super();
		this.parcelRepo = parcelRepo;
	}

	@Override
	public List<Parcel> findParcelsWithDetailsByShipperId(Integer shipperId) {
		return parcelRepo.findParcelsWithDetailsByShipperId(shipperId);
	}

	@Override
	public Optional<Parcel> findById(Integer id) {
		return parcelRepo.findById(id);
	}

	

	


	
	
}
