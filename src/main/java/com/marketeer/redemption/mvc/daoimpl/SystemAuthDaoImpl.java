package com.marketeer.redemption.mvc.daoimpl;

import com.marketeer.redemption.mvc.dao.SystemAuthDao;
import com.marketeer.redemption.mvc.entity.SystemAuthDo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by rtbkit on 18/4/17.
 */

@Repository
public class SystemAuthDaoImpl implements SystemAuthDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public SystemAuthDo getByEmailId(String emailId) {
        return (SystemAuthDo)factory.getCurrentSession().createCriteria(SystemAuthDo.class)
                .add(Restrictions.eq("emailAddress",emailId)).uniqueResult();
    }
}
