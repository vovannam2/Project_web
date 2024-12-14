package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Parcel;

public interface IParcelService {
	long revenueOnCurrentDate();

	long revenueOnCurrentWeek();

	long revenueOnCurrentMonth();

	long revenueOnCurrentYear();

	List<Long> getMonthlyTotalRevenue();

	List<Long> getQuarterlyTotalRevenue();

	long parcelOnCurrentDate();

	long parcelOnCurrentWeek();

	long parcelOnCurrentMonth();

	long parcelOnCurrentYear();

	List<Long> getMonthlyTotalParcel();

	List<Long> getQuarterlyTotalParcel();
	
	long countOneCustomer(Integer id);
	
	List<Parcel> findParcelOneCustomer(Integer id);
}
