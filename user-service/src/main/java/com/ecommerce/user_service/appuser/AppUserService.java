package com.ecommerce.user_service.appuser;


import com.ecommerce.user_service.appuser.user.AppUserDTO;
import com.ecommerce.user_service.util.PageDetails;

public interface AppUserService
{
    PageDetails<AppUserDTO> getAllUsers(int pageNum, int pageSize, String sortBy, String sortOrder);
}
