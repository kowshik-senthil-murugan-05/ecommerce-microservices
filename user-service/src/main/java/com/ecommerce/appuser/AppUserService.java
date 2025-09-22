package com.ecommerce.appuser;


import com.ecommerce.appuser.user.AppUserDTO;
import com.ecommerce.util.PageDetails;

public interface AppUserService
{
    PageDetails<AppUserDTO> getAllUsers(int pageNum, int pageSize, String sortBy, String sortOrder);
}
