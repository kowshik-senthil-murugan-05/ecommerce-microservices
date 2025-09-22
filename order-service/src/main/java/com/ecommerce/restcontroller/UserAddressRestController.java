package com.ecommerce.restcontroller;//package com.ecommerce.app.restcontroller;
//
//import com.ecommerce.app.appuser.address.UserAddressDTO;
//import com.ecommerce.app.appuser.address.UserAddressService;
//import com.ecommerce.app.exceptionhandler.APIResponse;
//import com.ecommerce.app.util.AppUtil;
//import com.ecommerce.app.util.PageDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/address")
//public class UserAddressRestController
//{
//    @Autowired
//    private UserAddressService addressService;
//
//
//    @PostMapping("/add/{userId}")
//    public ResponseEntity<APIResponse<UserAddressDTO>> addAddress(@PathVariable long userId, @RequestBody UserAddressDTO dto) {
//        UserAddressDTO userAddressDTO = addressService.addAddress(userId, dto);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Address created successfully!!",
//                        true,
//                        userAddressDTO),
//                HttpStatus.CREATED
//        );
//    }
//
//    @GetMapping("/user/get/allAddress/{userId}")
//    public ResponseEntity<PageDetails<UserAddressDTO>> getAllAddresses
//            (@PathVariable long userId,
//             @RequestParam(name = "pageNum", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNum,
//             @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//             @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
//             @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
//    {
//        PageDetails<UserAddressDTO> pageDetails = addressService.fetchAllAddressesForUser(userId, pageNum, pageSize, sortBy, sortOrder);
//
//        return new ResponseEntity<>(
//                pageDetails,
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/get/specificAddress/user/{userId}/{addressId}")
//    public ResponseEntity<UserAddressDTO> getAddress(@PathVariable long userId, @PathVariable long addressId) {
//        UserAddressDTO specificAddressForUser = addressService.fetchSpecificAddressForUser(userId, addressId);
//
//        return new ResponseEntity<>(
//                specificAddressForUser,
//                HttpStatus.OK
//        );
//    }
//
//
//    @PutMapping("/update/{userId}")
//    public ResponseEntity<APIResponse<UserAddressDTO>> updateAddress(@PathVariable long userId, @RequestBody UserAddressDTO dto) {
//        UserAddressDTO userAddressDTO = addressService.updateAddress(userId, dto);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Address updated successfully!!",
//                        true,
//                        userAddressDTO
//                ),
//                HttpStatus.OK
//        );
//    }
//
//    @DeleteMapping("/delete/{userId}/{addressId}")
//    public ResponseEntity<String> deleteAddress(@PathVariable long userId, @PathVariable long addressId) {
//        addressService.deleteAddress(userId, addressId);
//        return ResponseEntity.ok("Address deleted successfully");
//    }
//}
