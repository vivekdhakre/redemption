package com.marketeer.redemption.mvc.dao;

import com.marketeer.redemption.mvc.entity.MerchantSelectionDo;

/**
 * Created by Vivek on 24/4/17.
 */
public interface MerchantSelectionDao {

    MerchantSelectionDo getByMerchantId(long merchantId);

    void update(MerchantSelectionDo merchantSelectionDo);
}
