package com.marketeer.redemption.mvc.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "coupon_value")
public class CouponValueDo {

    @Id
    @GeneratedValue
    @Column(name = "coupon_value_pkey")
    private long couponValuePkey;

    @Column(name = "coupon_value")
    private String couponValue;

    @Column(name = "coupon_location")
    private String couponLocation;

    @Column(name = "coupon_type")
    private int couponType;

    @Column(name = "src")
    private String src;

    @Column(name = "plateform")
    private String plateform;

    @Column(name = "coupon_validity")
    private Timestamp couponValidDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "is_redeem")
    private long isRedeem;

    @Column(name = "redeemed_by")
    private long redeemedBy;

    @Column(name = "redeemed_on")
    private Date redeemedOn;


    @Column(name = "branch_id")
    private long branchid;

    @Column(name = "store_desc")
    private String storedesc;

    @Column(name = "appkey")
    private String appkey;

    @Column(name = "sku_invoice_fkey")
    private long skuInvoiceFkey;

    @Column(name = "pos_datetime")
    private Timestamp posDatetime;

    @ManyToOne
    @JoinColumn(name = "coupon_id_fkey")
    private CouponDo couponDo;


    public CouponValueDo() {

    }


    public long getCouponValuePkey() {
        return couponValuePkey;
    }

    public void setCouponValuePkey(long couponValuePkey) {
        this.couponValuePkey = couponValuePkey;
    }

    public CouponDo getCouponDo() {
        return couponDo;
    }

    public void setCouponDo(CouponDo couponDo) {
        this.couponDo = couponDo;
    }

    public String getCouponLocation() {
        return couponLocation;
    }

    public void setCouponLocation(String couponLocation) {
        this.couponLocation = couponLocation;
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public long getRedeemedBy() {
        return redeemedBy;
    }

    public void setRedeemedBy(long redeemedBy) {
        this.redeemedBy = redeemedBy;
    }

    public Date getRedeemedOn() {
        return redeemedOn;
    }

    public void setRedeemedOn(Date redeemedOn) {
        this.redeemedOn = redeemedOn;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }

    public long getIsRedeem() {
        return isRedeem;
    }

    public void setIsRedeem(long isRedeem) {
        this.isRedeem = isRedeem;
    }

    public long getBranchid() {
        return branchid;
    }

    public void setBranchid(long branchid) {
        this.branchid = branchid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }

    public String getStoredesc() {
        return storedesc;
    }

    public void setStoredesc(String storedesc) {
        this.storedesc = storedesc;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public long getSkuInvoiceFkey() {
        return skuInvoiceFkey;
    }

    public void setSkuInvoiceFkey(long skuInvoiceFkey) {
        this.skuInvoiceFkey = skuInvoiceFkey;
    }

    public Timestamp getCouponValidDate() {
        return couponValidDate;
    }

    public void setCouponValidDate(Timestamp couponValidDate) {
        this.couponValidDate = couponValidDate;
    }

    public Timestamp getPosDatetime() {
        return posDatetime;
    }

    public void setPosDatetime(Timestamp posDatetime) {
        this.posDatetime = posDatetime;
    }
}