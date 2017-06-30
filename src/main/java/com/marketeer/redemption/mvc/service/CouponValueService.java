package com.marketeer.redemption.mvc.service;



import com.marketeer.redemption.mvc.entity.MerchantDo;
import com.marketeer.redemption.util.Response;

import java.util.Map;

/**
 * Created by Vivek on 26/4/17.
 */
public interface CouponValueService {

    Response getInfo(String coupon, long merchantId);

    String redeemCoupon(long couponValueId, long branchId, long merchantId);

//    Map<String, String> getCount(MerchantDo merchantId);
}
