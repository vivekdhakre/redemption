package com.marketeer.redemption.mvc.dao;

import com.marketeer.redemption.mvc.entity.MerchantBranchDetailDo;

import java.util.List;

/**
 * Created by Vivek on 27/4/17.
 */
public interface MerchantBranchDetailDao {

    MerchantBranchDetailDo getByIdAndMId(long id, long merchantId);

    List<Object[]> getByDealId(long dealId);
}
