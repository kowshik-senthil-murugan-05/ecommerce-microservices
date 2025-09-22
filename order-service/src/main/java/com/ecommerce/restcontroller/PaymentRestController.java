//package com.ecommerce.restcontroller;
//
//import com.ecommerce.app.exceptionhandler.APIResponse;
//import com.ecommerce.app.payment.PaymentDTO;
//import com.ecommerce.app.payment.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentRestController
//{
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
//
//    @GetMapping("/get/forOrder/{orderId}")
//    public ResponseEntity<PaymentDTO> getPaymentForOrder(@PathVariable long orderId) {
//        PaymentDTO paymentForOrder = paymentService.getPaymentForOrder(orderId);
//
//        return new ResponseEntity<>(
//                paymentForOrder,
//                HttpStatus.OK
//        );
//    }
//
//
//}
