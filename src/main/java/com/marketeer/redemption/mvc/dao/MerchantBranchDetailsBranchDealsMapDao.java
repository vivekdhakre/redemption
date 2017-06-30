package com.marketeer.redemption.mvc.dao;

import java.util.List;

/**
 * Created by ahoy on 25/4/17.
 */
public interface MerchantBranchDetailsBranchDealsMapDao {

    public List<Object[]> getLatLngByMbdId(long merchantBranchDealsId);
}
