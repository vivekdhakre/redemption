package com.marketeer.redemption.mvc.daoimpl;

import com.marketeer.redemption.mvc.dao.MerchantBranchDealsDao;
import com.marketeer.redemption.mvc.entity.*;
import com.marketeer.redemption.util.StackTrace;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vivek on 24/4/17.
 */

@Repository
public class MerchantBranchDealsDaoImpl implements MerchantBranchDealsDao {

    private static Logger logger = LoggerFactory.getLogger(MerchantBranchDealsDaoImpl.class);

    @Autowired
    private SessionFactory factory;


    @Override
    public List<MerchantBranchDealsDo> getByMerchantEmail(String merchantEmail) {
        return factory.getCurrentSession().createCriteria(MerchantBranchDealsDo.class)
                .add(Restrictions.eq("createdBy", merchantEmail))
                .addOrder(Order.desc("createdOn")).list();
    }

    @Override
    public List<MerchantBranchDealsDo> get(String merchantEmail, int maxResult) {
        return factory.getCurrentSession().createCriteria(MerchantBranchDealsDo.class)
                .add(Restrictions.eq("createdBy", merchantEmail))
                .addOrder(Order.asc("dealStatus"))
                .addOrder(Order.desc("endDatetime"))
                .addOrder(Order.desc("createdOn"))
                .setMaxResults(maxResult).list();
    }

    @Override
    public MerchantBranchDealsDo addMerchantBranchDeal(MerchantBranchDealsDo merchantbranchdealsDo, CampaignPlansDo campaignPlansDo, MerchantDo merchantDo, List<MerchantBranchDetailsBranchDealsMapDo> branchList, List<MerchantBranchDealsBusinessCategoryMapDo> listB, List<DealsValidDaysDo> dealsValidDaysDoList) {
        try {
            factory.getCurrentSession().save(campaignPlansDo);

            MerchantCampaignPlansMapDo merchantCampaignPlansMapDo = new MerchantCampaignPlansMapDo(campaignPlansDo.getCreatedBy(), campaignPlansDo.getUpdatedBy(), campaignPlansDo, merchantDo);
            factory.getCurrentSession().save(merchantCampaignPlansMapDo);

            merchantbranchdealsDo.setMcpmId(merchantCampaignPlansMapDo.getMerchantPlanMapPkey());
            factory.getCurrentSession().save(merchantbranchdealsDo);

            branchList.forEach(merchantBranchDetailsBranchDealsMapDo -> {
                merchantBranchDetailsBranchDealsMapDo.setMerchantBranchDeals(merchantbranchdealsDo);
                factory.getCurrentSession().save(merchantBranchDetailsBranchDealsMapDo);
            });

            listB.forEach(merchantBranchDealsBusinessCategoryMapDo -> {
                merchantBranchDealsBusinessCategoryMapDo.setMerchantBranchDeals(merchantbranchdealsDo);
                factory.getCurrentSession().save(merchantBranchDealsBusinessCategoryMapDo);
            });

            dealsValidDaysDoList.forEach(dealsValidDaysDo -> {
                dealsValidDaysDo.setMerchantBranchDeals(merchantbranchdealsDo);
                factory.getCurrentSession().save(dealsValidDaysDo);
            });

        } catch (Exception e) {
            logger.error(StackTrace.getRootCause(e, getClass().getName()));
            throw e;
        }
        return merchantbranchdealsDo;

    }
}
