package com.marketeer.redemption.mvc.daoimpl;

import com.marketeer.redemption.mvc.dao.MerchantDao;
import com.marketeer.redemption.mvc.entity.MerchantDo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by rtbkit on 19/4/17.
 */

@Repository
public class MerchantDaoImpl implements MerchantDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public MerchantDo getBySystemAuthFkey(long systemAuthPkey) {
        return (MerchantDo)factory.getCurrentSession().createCriteria(MerchantDo.class)
                .add(Restrictions.eq("systemAuthFkey",systemAuthPkey)).uniqueResult();
    }

    @Override
    public MerchantDo getById(long id) {
        return (MerchantDo)factory.getCurrentSession().get(MerchantDo.class,id);
    }
}
