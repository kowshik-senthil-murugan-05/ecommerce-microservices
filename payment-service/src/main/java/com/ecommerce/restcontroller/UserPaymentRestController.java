//package com.ecommerce.restcontroller;
//
//import com.ecommerce.app.exceptionhandler.APIResponse;
//import com.ecommerce.app.payment.PaymentDTO;
//import com.ecommerce.app.payment.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/user/payment")
//public class UserPaymentRestController
//{
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @PostMapping("/make")
//    public ResponseEntity<APIResponse<PaymentDTO>> makePayment(@RequestBody PaymentDTO dto) {
//        PaymentDTO paymentDTO = paymentService.makePayment(dto);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Payment done!!",
//                        true,
//                        paymentDTO
//                ),
//                HttpStatus.OK
//        );
//    }
//}
