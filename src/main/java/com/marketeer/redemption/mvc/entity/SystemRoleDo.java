package com.marketeer.redemption.mvc.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="system_role")
public class SystemRoleDo  implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name="advert_role_pkey")
     private long advertRolePkey;

    @Column(name="role_name")
     private String roleName;

    @Column(name="created_by")
     private String createdBy;

    @Column(name="created_on")
     private Date createdOn;

    @Column(name="updated_by")
     private String updatedBy;

    @Column(name="updated_on")
     private Date updatedOn;


    public long getAdvertRolePkey() {
        return advertRolePkey;
    }

    public void setAdvertRolePkey(long advertRolePkey) {
        this.advertRolePkey = advertRolePkey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    @Override
    public String toString() {
        return "SystemRoleDo{" +
                "advertRolePkey=" + advertRolePkey +
                ", roleName='" + roleName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                '}';
    }
}