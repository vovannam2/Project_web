package vn.iostar.controllers.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iostar.service.IParcelService;

@Controller
@RequestMapping("/admin")
public class DashboardController {
	
	@Autowired(required = true)
	IParcelService parcelService;
	
	@RequestMapping("")
	public String dashboard(ModelMap model) {
		
		
		model.addAttribute("reDay",parcelService.revenueOnCurrentDate());
		model.addAttribute("reWeek",parcelService.revenueOnCurrentWeek());
		model.addAttribute("reMonth",parcelService.revenueOnCurrentMonth());
		model.addAttribute("reYear",parcelService.revenueOnCurrentYear());
		model.addAttribute("totalMonthly", parcelService.getMonthlyTotal());
		model.addAttribute("totalQuarter",parcelService.getQuarterlyTotal());

		return "admin/dashboard/revenue";
	}
}
