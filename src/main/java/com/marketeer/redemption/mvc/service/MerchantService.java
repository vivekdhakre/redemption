package com.marketeer.redemption.mvc.service;

import com.marketeer.redemption.mvc.entity.MerchantDo;

/**
 * Created by rtbkit on 19/4/17.
 */
public interface MerchantService {

    public MerchantDo getBySystemAuthFkey(long systemAuthPkey);

    MerchantDo getById(long id);
}
