//package com.marketeer.redemption.mvc.serviceimpl;
//
//import com.marketeer.mvc.dao.MerchantBranchDealsDao;
//import com.marketeer.mvc.dao.MerchantBranchDetailsBranchDealsMapDao;
//import com.marketeer.mvc.dao.MerchantSelectionDao;
//import com.marketeer.mvc.entity.*;
//import com.marketeer.mvc.service.CampaignService;
//import com.marketeer.mvc.spring.security.util.Status;
//import com.marketeer.redemption.mvc.dao.MerchantBranchDealsDao;
//import com.marketeer.redemption.mvc.dao.MerchantBranchDetailsBranchDealsMapDao;
//import com.marketeer.redemption.mvc.dao.MerchantSelectionDao;
//import com.marketeer.redemption.mvc.entity.CampaignPlansDo;
//import com.marketeer.redemption.mvc.entity.MerchantBranchDealsDo;
//import com.marketeer.redemption.mvc.entity.SystemAuthDo;
//import com.marketeer.redemption.mvc.service.CampaignService;
//import com.marketeer.util.Constants;
//import com.marketeer.util.StackTrace;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.sql.Time;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.List;
//
///**
// * Created by Vivek on 22/4/17.
// */
//
//@Service
//public class CampaignServiceImpl implements CampaignService {
//
//    private static Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);
//
//    @Autowired
//    private MerchantBranchDealsDao merchantBranchDealsDao;
//
//    @Autowired
//    private MerchantSelectionDao merchantSelectionDao;
//
//    @Autowired
//    private MerchantBranchDetailsBranchDealsMapDao merchantBranchDetailsBranchDealsMapDao;
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<MerchantBranchDealsDo> getmerchantBranchDealList(String merchantEmail) {
//
//        List<MerchantBranchDealsDo> merchantBranchDealsDos = merchantBranchDealsDao.getByMerchantEmail(merchantEmail);
//        merchantBranchDealsDos = setStatus(merchantBranchDealsDos);
//
//        return merchantBranchDealsDos;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<MerchantBranchDealsDo> getMerchantBranchDealList(String merchantEmail, int maxResult) {
//        List<MerchantBranchDealsDo> merchantBranchDealsDos = null;
//        try {
//            merchantBranchDealsDos = merchantBranchDealsDao.get(merchantEmail, maxResult);
//            if (merchantBranchDealsDos != null && merchantBranchDealsDos.size() > 0) {
//                merchantBranchDealsDos = setStatus(merchantBranchDealsDos);
//                merchantBranchDealsDos.sort((o1, o2) -> o1.getDealStatus().compareTo(o2.getDealStatus()));
//            }
//
//        } catch (Exception e) {
//            logger.error("merchantEmail: " + merchantEmail + " | maxResult: " + maxResult + " | " + StackTrace.getRootCause(e, getClass().getName()));
//        }
//        return merchantBranchDealsDos;
//
//    }
//
//
//}
