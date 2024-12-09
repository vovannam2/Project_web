package vn.iostar.service;

import java.util.List;

import vn.iostar.model.dto.RevenueDTO;

import org.springframework.data.domain.Page;

public interface IRevenueService {
	List<RevenueDTO> findOfficeRevenue();
	
	Page<RevenueDTO> getAll(Integer pageNo);
}
