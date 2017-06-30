package com.marketeer.redemption.mvc.daoimpl;

import com.marketeer.redemption.mvc.dao.MerchantSelectionDao;
import com.marketeer.redemption.mvc.entity.MerchantSelectionDo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Vivek on 24/4/17.
 */

@Repository
public class MerchantSelectionDaoImpl implements MerchantSelectionDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public MerchantSelectionDo getByMerchantId(long merchantId) {
        return (MerchantSelectionDo) factory.getCurrentSession().createCriteria(MerchantSelectionDo.class)
                .add(Restrictions.eq("merchant.merchantPkey", merchantId))
                .uniqueResult();
    }

    @Override
    public void update(MerchantSelectionDo merchantSelectionDo) {
        factory.getCurrentSession().update(merchantSelectionDo);
    }
}
