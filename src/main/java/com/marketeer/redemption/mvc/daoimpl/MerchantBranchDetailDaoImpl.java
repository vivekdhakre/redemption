package com.marketeer.redemption.mvc.daoimpl;


import com.marketeer.redemption.mvc.dao.MerchantBranchDetailDao;
import com.marketeer.redemption.mvc.entity.MerchantBranchDetailDo;
import com.marketeer.redemption.mvc.entity.MerchantBranchDetailsBranchDealsMapDo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ahoy on 27/4/17.
 */
@Repository
public class MerchantBranchDetailDaoImpl implements MerchantBranchDetailDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public MerchantBranchDetailDo getByIdAndMId(long id, long merchantId) {
        return (MerchantBranchDetailDo) factory.getCurrentSession().createCriteria(MerchantBranchDetailDo.class)
                .add(Restrictions.eq("merchantBranchPkey", id))
                .add(Restrictions.eq("merchant.merchantPkey", merchantId))
                .uniqueResult();
    }

    @Override
    public List<Object[]> getByDealId(long dealId) {

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("mbdetail.merchantBranchPkey"));
        projectionList.add(Projections.property("mbdetail.branchName"));
        projectionList.add(Projections.property("c.city"));

        return factory.getCurrentSession().createCriteria(MerchantBranchDetailsBranchDealsMapDo.class)
                .createAlias("merchantBranchDeals", "mbd")
                .createAlias("merchantBranchDetail", "mbdetail")
                .createAlias("mbdetail.city", "c")
                .add(Restrictions.eq("mbd.merchantDealsPkey", dealId))
                .setProjection(projectionList)
                .list();
    }
}
