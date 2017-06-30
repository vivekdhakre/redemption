package com.marketeer.redemption.mvc.serviceimpl;

import com.marketeer.redemption.mvc.dao.MerchantDao;
import com.marketeer.redemption.mvc.entity.MerchantDo;
import com.marketeer.redemption.mvc.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rtbkit on 19/4/17.
 */

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDao merchantDao;

    @Override
    @Transactional(readOnly = true)
    public MerchantDo getBySystemAuthFkey(long systemAuthPkey) {
        return merchantDao.getBySystemAuthFkey(systemAuthPkey);
    }

    @Override
    @Transactional(readOnly = true)
    public MerchantDo getById(long id) {
        return merchantDao.getById(id);
    }
}
