package com.marketeer.redemption.mvc.dao;


import com.marketeer.redemption.mvc.entity.SystemAuthDo;

/**
 * Created by rtbkit on 18/4/17.
 */
public interface SystemAuthDao {

    public SystemAuthDo getByEmailId(String emailId);
}
