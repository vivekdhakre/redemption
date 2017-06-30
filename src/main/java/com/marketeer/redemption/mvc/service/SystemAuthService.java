package com.marketeer.redemption.mvc.service;


import com.marketeer.redemption.mvc.entity.SystemAuthDo;

/**
 * Created by rtbkit on 18/4/17.
 */
public interface SystemAuthService {
    SystemAuthDo getByEmailId(String emailId);
}
