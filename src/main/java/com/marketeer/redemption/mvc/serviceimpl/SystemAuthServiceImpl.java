package com.marketeer.redemption.mvc.serviceimpl;


import com.marketeer.redemption.mvc.dao.SystemAuthDao;
import com.marketeer.redemption.mvc.entity.SystemAuthDo;
import com.marketeer.redemption.mvc.service.SystemAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rtbkit on 18/4/17.
 */

@Service
public class SystemAuthServiceImpl implements SystemAuthService {

    @Autowired
    private SystemAuthDao systemAuthDao;

    @Transactional
    public SystemAuthDo getByEmailId(String emailId){
        return systemAuthDao.getByEmailId(emailId);
    }
}
