package com.marketeer.redemption.mvc.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "advert_user")
public class AdvertUserDo implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_pkey")
    private long userPkey;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "secondary_mobile_no")
    private String secondaryMobileNo;

    @Column(name = "gender")
    private String gender;

    @Column(name = "member_photo")
    private String memberPhoto;

    @Column(name = "occupation")
    private Short occupation;

    @Column(name = "dob_dd")
    private Short dobDd;

    @Column(name = "dob_yyyy")
    private Short dobYyyy;

    @Column(name = "dob_mm")
    private Short dobMm;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "system_auth_user_fkey")
    private SystemAuthUserDo systemAuthUserDo;

//    private CityDo city;


    public long getUserPkey() {
        return userPkey;
    }

    public void setUserPkey(long userPkey) {
        this.userPkey = userPkey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondaryMobileNo() {
        return secondaryMobileNo;
    }

    public void setSecondaryMobileNo(String secondaryMobileNo) {
        this.secondaryMobileNo = secondaryMobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public Short getOccupation() {
        return occupation;
    }

    public void setOccupation(Short occupation) {
        this.occupation = occupation;
    }

    public Short getDobDd() {
        return dobDd;
    }

    public void setDobDd(Short dobDd) {
        this.dobDd = dobDd;
    }

    public Short getDobYyyy() {
        return dobYyyy;
    }

    public void setDobYyyy(Short dobYyyy) {
        this.dobYyyy = dobYyyy;
    }

    public Short getDobMm() {
        return dobMm;
    }

    public void setDobMm(Short dobMm) {
        this.dobMm = dobMm;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public SystemAuthUserDo getSystemAuthUserDo() {
        return systemAuthUserDo;
    }

    public void setSystemAuthUserDo(SystemAuthUserDo systemAuthUserDo) {
        this.systemAuthUserDo = systemAuthUserDo;
    }
}


