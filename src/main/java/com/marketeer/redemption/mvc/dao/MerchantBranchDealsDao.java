package com.marketeer.redemption.mvc.dao;

import com.marketeer.redemption.mvc.entity.*;

import java.util.List;

/**
 * Created by ahoy on 24/4/17.
 */
public interface MerchantBranchDealsDao {

    MerchantBranchDealsDo addMerchantBranchDeal(MerchantBranchDealsDo merchantbranchdealsDo, CampaignPlansDo campaignPlansDo, MerchantDo merchantDo, List<MerchantBranchDetailsBranchDealsMapDo> branchList, List<MerchantBranchDealsBusinessCategoryMapDo> listB, List<DealsValidDaysDo> dealsValidDaysDoList);

    List<MerchantBranchDealsDo> getByMerchantEmail(String merchantEmail);

    List<MerchantBranchDealsDo> get(String merchantEmail, int maxResult);

}
