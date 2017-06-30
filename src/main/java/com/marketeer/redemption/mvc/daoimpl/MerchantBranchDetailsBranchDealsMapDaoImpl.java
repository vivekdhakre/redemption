package com.marketeer.redemption.mvc.daoimpl;

import com.marketeer.redemption.mvc.dao.MerchantBranchDetailsBranchDealsMapDao;
import com.marketeer.redemption.mvc.entity.MerchantBranchDetailsBranchDealsMapDo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vivek on 25/4/17.
 */

@Repository
public class MerchantBranchDetailsBranchDealsMapDaoImpl implements MerchantBranchDetailsBranchDealsMapDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Object[]> getLatLngByMbdId(long merchantBranchDealsId) {
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("mbd.latitude"))
                .add(Projections.property("mbd.longitude"))
                .add(Projections.property("mbd.branchName"))
                .add(Projections.property("m.merchantName"))
                .add(Projections.property("m.merchantPkey"));


        List<Object[]> list = factory.getCurrentSession().createCriteria(MerchantBranchDetailsBranchDealsMapDo.class)
                .add(Restrictions.eq("merchantBranchDeals.merchantDealsPkey", merchantBranchDealsId))
                .createAlias("merchantBranchDetail", "mbd")
                .createAlias("mbd.merchant", "m")
                .setProjection(projectionList)
                .list();

        return list;
    }
}
