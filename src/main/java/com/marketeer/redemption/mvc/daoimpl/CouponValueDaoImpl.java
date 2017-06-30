package com.marketeer.redemption.mvc.daoimpl;


import com.marketeer.redemption.mvc.dao.CouponValueDao;
import com.marketeer.redemption.mvc.entity.CouponValueDo;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ahoy on 26/4/17.
 */

@Repository
public class CouponValueDaoImpl implements CouponValueDao {

    @Autowired
    private SessionFactory factory;

    @Override
    @Transactional
    public List<CouponValueDo> getByCoupon(String coupon) {
        return factory.getCurrentSession().createCriteria(CouponValueDo.class)
                .add(Restrictions.eq("couponValue", coupon)).list();
    }

    @Override
    public List<Object[]> getByCouponAndMerchantId(String coupon, long merchantId) {

        ProjectionList projectionList = Projections.projectionList();
        projectionList
                .add(Projections.distinct(Projections.property("couponValuePkey")))
                .add(Projections.property("couponValidDate"))
                .add(Projections.property("createdBy"))
                .add(Projections.property("createdOn"))
                .add(Projections.property("isRedeem"))
                .add(Projections.property("redeemedOn"))
                .add(Projections.property("branchid"))
                .add(Projections.property("mbd.shortDescription"))
                .add(Projections.property("mbd.merchantDealsPkey"));


        Criteria criteria = factory.getCurrentSession().createCriteria(CouponValueDo.class)
                .add(Restrictions.eq("couponValue", coupon))
                .add(Restrictions.eq("couponType", 0))
                .addOrder(Order.asc("isRedeem"))
                .addOrder(Order.asc("couponValidDate"))
                .createAlias("couponDo", "c")
                .createAlias("c.merchantBranchDealsDoList", "mbd")
                .createAlias("mbd.merchantBranchDetailsBranchDealsMaps", "mbdbdms")
                .createAlias("mbdbdms.merchantBranchDetail", "mbdtl")
                .createAlias("mbdtl.merchant", "m")
                .add(Restrictions.eq("m.merchantPkey", merchantId))
                .setProjection(projectionList);

        return criteria.list();
    }

    @Override
    public CouponValueDo getById(long id) {
        return (CouponValueDo) factory.getCurrentSession().get(CouponValueDo.class, id);
    }

    @Override
    public int getCount(Date fDate, Date tDate, long merchantId, String colName) {
        return Integer.valueOf(factory.getCurrentSession().createCriteria(CouponValueDo.class)
                .add(Restrictions.eq("couponType", 0))
                .add(Restrictions.between(colName, fDate, tDate))
                .add(Restrictions.ne("couponValue", ""))
                .add(Restrictions.isNotNull("couponValue"))
                .createAlias("couponDo", "c")
                .add(Restrictions.eq("c.createdBy", String.valueOf(merchantId)))
                .setProjection(Projections.rowCount()).list().get(0).toString());
    }

    @Override
    public List<Object[]> getCountGroupByMonth(Date fDate, Date tDate, long merchantId, String colName) {

        List<Object[]> list = factory.getCurrentSession().createQuery("select to_char(date(" + colName + "),'Mon-YY'),count(*) from CouponValueDo where couponType=0 and couponDo.createdBy='" + String.valueOf(merchantId) + "' and " + colName + " between '" + fDate + "' and '" + tDate + "' group by 1").list();
        return list;
    }
}
