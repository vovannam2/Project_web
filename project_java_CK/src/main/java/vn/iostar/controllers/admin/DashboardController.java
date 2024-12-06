package vn.iostar.controllers.admin;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DashboardController {
	
	@RequestMapping({"", "/"})
	public String dashboard(ModelMap model) {
		
		Locale localeVietnam = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVietnam);
		
		model.addAttribute("reMonth",10);
		model.addAttribute("reYear",10);
		model.addAttribute("reQuarter",10);
		model.addAttribute("rateCom",10);
		
		model.addAttribute("totalMontly", 10);
		model.addAttribute("totalQuarter",10);

		return "admin/dashboard";
	}
}
