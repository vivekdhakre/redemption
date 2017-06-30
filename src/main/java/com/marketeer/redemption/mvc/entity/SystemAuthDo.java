package com.marketeer.redemption.mvc.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="system_auth")
public class SystemAuthDo  implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "advert_auth_pkey")
     private long advertAuthPkey;

     @Column(name="email_address")
     private String emailAddress;

     @Column(name="password")
     private String password;

     @Column(name="created_by")
     private String createdBy;

     @Column(name="created_on")
     private Date createdOn;

     @Column(name="updated_by")
     private String updatedBy;

     @Column(name="updated_on")
     private Date updatedOn;

    @ManyToOne
    @JoinColumn(name="role_fkey")
    private SystemRoleDo systemRole;

    public long getAdvertAuthPkey() {
        return advertAuthPkey;
    }

    public void setAdvertAuthPkey(long advertAuthPkey) {
        this.advertAuthPkey = advertAuthPkey;
    }

    public SystemRoleDo getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRoleDo systemRole) {
        this.systemRole = systemRole;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


}


