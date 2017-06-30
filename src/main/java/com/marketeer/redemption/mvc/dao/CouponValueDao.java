package com.marketeer.redemption.mvc.dao;


import com.marketeer.redemption.mvc.entity.CouponValueDo;

import java.util.Date;
import java.util.List;

/**
 * Created by ahoy on 26/4/17.
 */
public interface CouponValueDao {

    List<CouponValueDo> getByCoupon(String coupon);

    List<Object[]> getByCouponAndMerchantId(String coupon, long merchantId);

    CouponValueDo getById(long id);

    int getCount(Date fDate, Date tDate, long merchantId, String colName);

    List<Object[]> getCountGroupByMonth(Date fDate, Date tDate, long merchantId, String colName);
}
