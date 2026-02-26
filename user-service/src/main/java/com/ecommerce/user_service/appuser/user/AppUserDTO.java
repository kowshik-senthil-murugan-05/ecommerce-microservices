package com.ecommerce.user_service.appuser.user;



import com.ecommerce.user_service.appuser.address.UserAddressDTO;
import com.ecommerce.user_service.appuser.role.UserRole;

import java.util.List;

public class AppUserDTO
{
    public long userId;
    public String userName;
    public String email;
    public String password;
    public List<UserRole.Role> roleIds;
    public List<UserAddressDTO> userAddressDTOS;
}
