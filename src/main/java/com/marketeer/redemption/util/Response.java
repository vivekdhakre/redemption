package com.marketeer.redemption.util;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ahoy on 26/4/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Header header;

    private CouponDetails couponDetails;

    public Response(Header header) {
        this.header = header;
    }

    public Response(Header header, CouponDetails couponDetails) {
        this.header = header;
        this.couponDetails = couponDetails;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public CouponDetails getCouponDetails() {
        return couponDetails;
    }

    public void setCouponDetails(CouponDetails couponDetails) {
        this.couponDetails = couponDetails;
    }
}