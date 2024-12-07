package vn.iostar.service.shipper;

import java.util.List;
import java.util.Optional;

import vn.iostar.entity.Parcel;

public interface IShipperService {


	List<Parcel> findParcelsWithDetailsByShipperId(Integer shipperId);

	Optional<Parcel> findById(Integer id);

	

	

}
