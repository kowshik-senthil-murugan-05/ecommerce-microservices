package com.ecommerce.appuser.user;



import com.ecommerce.appuser.address.UserAddressDTO;
import com.ecommerce.appuser.role.UserRole;

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
