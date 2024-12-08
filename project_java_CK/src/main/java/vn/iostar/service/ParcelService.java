package vn.iostar.service;

import java.util.List;

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
	public long revenueOnCurrentDate() {
		return parcelRepository.revenueOnCurrentDate();
	}

	@Override
	public long revenueOnCurrentWeek() {
		return parcelRepository.revenueOnCurrentWeek();
	}

	@Override
	public long revenueOnCurrentMonth() {
		return parcelRepository.revenueOnCurrentMonth();
	}

	@Override
	public long revenueOnCurrentYear() {
		return parcelRepository.revenueOnCurrentYear();
	}

	@Override
	public List<Long> getMonthlyTotalRevenue() {
		return parcelRepository.getMonthlyTotalRevenue();
	}

	@Override
	public List<Long> getQuarterlyTotalRevenue() {
		return parcelRepository.getQuarterlyTotalRevenue();
	}

	@Override
	public long parcelOnCurrentDate() {
		return parcelRepository.parcelOnCurrentDate();
	}

	@Override
	public long parcelOnCurrentWeek() {
		return parcelRepository.parcelOnCurrentWeek();
	}

	@Override
	public long parcelOnCurrentMonth() {
		return parcelRepository.parcelOnCurrentMonth();
	}

	@Override
	public long parcelOnCurrentYear() {
		return parcelRepository.parcelOnCurrentYear();
	}

	@Override
	public List<Long> getMonthlyTotalParcel() {
		return parcelRepository.getMonthlyTotalParcel();
	}

	@Override
	public List<Long> getQuarterlyTotalParcel() {
		return parcelRepository.getQuarterlyTotalParcel();
	}

}
