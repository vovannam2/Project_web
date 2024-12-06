package vn.iostar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.repository.ParcelRepository;

@Service
public class ParcelService implements IParcelService{
	@Autowired
	ParcelRepository parcelRepository;
	
	public ParcelService(ParcelRepository parcelRepository) {
		this.parcelRepository = parcelRepository;
	}
	
	@Override
	public Long countParcelsCreatedToday() {
		// TODO Auto-generated method stub
		return null;
	}

}
