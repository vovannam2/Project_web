package vn.iostar.controllers.Payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iostar.service.PaymentService.IPayMethodService;

@Controller
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    IPayMethodService payMethodService;

    @GetMapping
    public String showPaymentForm() {
        return "payment_form";
    }

    @GetMapping("/create_payment") // Xử lý thanh toán
    public String createPayment(@RequestParam(value = "cost", defaultValue = "10000") Long cost,
                                @RequestParam(value = "data", defaultValue = "default data") String data) {
        // Xử lý logic thanh toán tại đây
        System.out.println("Thanh toán với chi phí: " + cost);
        System.out.println("Thông tin đơn hàng: " + data);

        // Chuyển hướng tới trang thành công
        return "payment/Order_User";
    }

    @GetMapping("/payment/success") // Trang thông báo thanh toán thành công
    public String paymentSuccess() {
        return "payment_success";
    }

}
