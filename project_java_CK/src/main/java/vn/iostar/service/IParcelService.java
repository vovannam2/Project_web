package vn.iostar.service;

import java.util.List;

public interface IParcelService {
	long revenueOnCurrentDate();
	
	long revenueOnCurrentWeek();
	
	long revenueOnCurrentMonth();
	
	long revenueOnCurrentYear();
	
	List<Long> getMonthlyTotal();
	
	List<Long> getQuarterlyTotal();
}
