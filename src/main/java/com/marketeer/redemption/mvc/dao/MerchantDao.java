package com.marketeer.redemption.mvc.dao;

import com.marketeer.redemption.mvc.entity.MerchantDo;

/**
 * Created by rtbkit on 19/4/17.
 */
public interface MerchantDao {

    MerchantDo getBySystemAuthFkey(long systemAuthPkey);

    MerchantDo getById(long id);
}
