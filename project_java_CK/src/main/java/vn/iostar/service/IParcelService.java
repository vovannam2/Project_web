package vn.iostar.service;

import java.util.List;

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
}
