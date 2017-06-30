package com.marketeer.redemption.util;

import java.util.List;

/**
 * Created by Vivek on 27/4/17.
 */
public class CouponDetails {

    public Integer isRedeem;
    private long id;
    private String coupon;
    private String campaign;
    private String status;
    private String name;
    private String email;
    private String msisdn;
    private String deviceid;
    private String googleid;
    private String createdOn;
    private String redeemedOn;

    private String branch;

    private String validity;

    private List<Object[]> branchList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getGoogleid() {
        return googleid;
    }

    public void setGoogleid(String googleid) {
        this.googleid = googleid;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getRedeemedOn() {
        return redeemedOn;
    }

    public void setRedeemedOn(String redeemedOn) {
        this.redeemedOn = redeemedOn;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Integer getIsRedeem() {
        return isRedeem;
    }

    public void setIsRedeem(Integer isRedeem) {
        this.isRedeem = isRedeem;
    }

    public List<Object[]> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Object[]> branchList) {
        this.branchList = branchList;
    }
}
