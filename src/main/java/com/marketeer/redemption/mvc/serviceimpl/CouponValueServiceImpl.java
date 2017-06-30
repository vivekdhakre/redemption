package com.marketeer.redemption.mvc.serviceimpl;


import com.marketeer.redemption.mvc.dao.CouponValueDao;
import com.marketeer.redemption.mvc.dao.MerchantBranchDetailDao;
import com.marketeer.redemption.mvc.dao.SystemAuthUserDao;
import com.marketeer.redemption.mvc.entity.CouponDo;
import com.marketeer.redemption.mvc.entity.CouponValueDo;
import com.marketeer.redemption.mvc.entity.MerchantBranchDetailDo;
import com.marketeer.redemption.mvc.service.CouponValueService;

import com.marketeer.redemption.util.CouponDetails;
import com.marketeer.redemption.util.Header;
import com.marketeer.redemption.util.Response;
import com.marketeer.redemption.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Vivek on 26/4/17.
 */

@Service
public class CouponValueServiceImpl implements CouponValueService {

    private static Logger logger = LoggerFactory.getLogger(CouponValueServiceImpl.class);

    @Autowired
    private CouponValueDao couponValueDao;

    @Autowired
    private SystemAuthUserDao systemAuthUserDao;

    @Autowired
    private MerchantBranchDetailDao merchantBranchDetailDao;

    @Override
    @Transactional(readOnly = true)
    public Response getInfo(String coupon, long merchantId) {
        Response response = null;
        try {
            List<Object[]> objectList = couponValueDao.getByCouponAndMerchantId(coupon, merchantId);
            CouponDetails couponDetails = new CouponDetails();
            if (!objectList.isEmpty() && objectList.size() > 0) {
                DateFormat format2 = new SimpleDateFormat("dd-MMM-yy HH:mm");

                Object[] objects = objectList.get(0);

                long couponValueId = Long.valueOf(objects[0].toString());
                Timestamp validDate = (Timestamp) objects[1];
                long advertId = Long.valueOf(objects[2].toString());
                Date createdOn = (Date) objects[3];
                int isRedeem = Integer.valueOf(objects[4].toString());
                Timestamp redeemedOn = (Timestamp) objects[5];
                long branchId = Long.valueOf(objects[6].toString());
                String description = objects[7].toString();
                long dealid = Long.valueOf(objects[8].toString());

                Object[] userObj = systemAuthUserDao.getByAdvertId(advertId);
                String firstName = userObj[0].toString();
                String lastName = userObj[1].toString();
                String emailAddress = userObj[2].toString();
                String phoneNumber = userObj[3].toString();


                couponDetails.setId(couponValueId);
                couponDetails.setCoupon(coupon);
                couponDetails.setCampaign(description);
                couponDetails.setCreatedOn(format2.format(createdOn));
                couponDetails.setIsRedeem(isRedeem);

                String[] splt = emailAddress.split("@");
                String splt0 = splt[0];
                String splt1 = splt[1];

                if (splt1.equalsIgnoreCase("devid.com") || splt1.equalsIgnoreCase("gid.com")) {
                    if (splt1.equalsIgnoreCase("devid.com")) {
                        couponDetails.setDeviceid(splt0);
                    } else {
                        couponDetails.setGoogleid(splt0);
                    }
                } else {
                    if (splt0.matches("[0-9]{10}")) {
                        couponDetails.setMsisdn(splt0);
                    } else {
                        couponDetails.setName(firstName + " " + lastName);
                        couponDetails.setEmail(emailAddress);
                        couponDetails.setMsisdn(phoneNumber);
                    }
                }

                if (isRedeem == 1) {
                    couponDetails.setStatus("Redeemed");
                    couponDetails.setRedeemedOn(format2.format(redeemedOn));

                    MerchantBranchDetailDo merchantBranchDetailDo = merchantBranchDetailDao.getByIdAndMId(branchId, merchantId);
                    couponDetails.setBranch(merchantBranchDetailDo != null ? merchantBranchDetailDo.getAddressLine2() + ", " + merchantBranchDetailDo.getCity().getCity() : branchId + "");

                } else {
                    couponDetails.setValidity(format2.format(validDate));
                    if (validDate.after(new Timestamp(new Date().getTime()))) {
                        if (isRedeem == 0) {

                            couponDetails.setStatus("Valid");
                            couponDetails.setBranchList(merchantBranchDetailDao.getByDealId(dealid));
                        } else {
                            couponDetails.setStatus("Unknown");
                        }
                    } else {
                        couponDetails.setStatus("Expired");
                    }
                }

                response = new Response(new Header(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()), couponDetails);
            } else {
                response = new Response(new Header(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
            }
        } catch (Exception e) {
            response = new Response(new Header(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
            logger.error("coupon: " + coupon + " | " + StackTrace.getRootCause(e, getClass().getName()));
        }

        return response;
    }

    @Override
    @Transactional
    public String redeemCoupon(long couponValueId, long branchId, long merchantId) {
        String resp = null;
        try {

            CouponValueDo couponValueDo = couponValueDao.getById(couponValueId);
            if (couponValueDo.getIsRedeem() == 0) {
                couponValueDo.setIsRedeem(1);
                couponValueDo.setRedeemedBy(merchantId);
                couponValueDo.setBranchid(branchId);
                couponValueDo.setRedeemedOn(new Date());

                CouponDo couponDo = couponValueDo.getCouponDo();
                couponDo.setNumberOfRedemption(couponDo.getNumberOfRedemption() + 1);
                couponDo.setUpdatedOn(new Date());

                resp = "Successfully Redeemed";

            } else {
                resp = "Already redeemed";
            }

        } catch (Exception e) {
            resp = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            logger.error("couponValueId: " + couponValueId + " | branchId: " + branchId + " | merchantId: " + merchantId + " | " + StackTrace.getRootCause(e, getClass().getName()));
        }

        return resp;
    }
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public Map<String, String> getCount(MerchantDo merchantDo) {
//        Map<String, String> map = new HashMap<>();
//        try {
//            int nOfMonth = 6;
//            SimpleDateFormat format1 = new SimpleDateFormat("MMM-yy");
//            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            List<String> monthList = new LinkedList<>();
//            int i = nOfMonth;
//            Calendar cal = Calendar.getInstance();
//            int year = cal.get(Calendar.YEAR);
//            Date tDate = new Date();
//            Date fDate = new Date();
//
//            do {
//                i--;
//                cal = Calendar.getInstance();
//                int month = ((cal.get(Calendar.MONTH)) + 1 - i);
//                int yr = month <= 0 ? year - 1 : year;
//                month = month <= 0 ? 12 + month : month;
//                cal.set(yr, month - 1, 1);
//                monthList.add(format1.format(cal.getTime()));
//
//                if (i == nOfMonth - 1) {
//                    fDate = format3.parse(format2.format(cal.getTime()) + " 00:00:00");
//                }
//
//            } while (i > 0);
//
//            fDate = fDate.before(merchantDo.getCreatedOn()) ? merchantDo.getCreatedOn() : fDate;
//
//            List<Object[]> rdmList = couponValueDao.getCountGroupByMonth(fDate, tDate, merchantDo.getMerchantPkey(), "redeemedOn");
//            List<Object[]> genList = couponValueDao.getCountGroupByMonth(fDate, tDate, merchantDo.getMerchantPkey(), "createdOn");
//
//            Gson gson = new Gson();
//
//            map.put("0", gson.toJson(setAndSort(genList, monthList)));
//            map.put("1", gson.toJson(setAndSort(rdmList, monthList)));
//
//        } catch (Exception e) {
//            logger.error("merchantId: " + merchantDo.getMerchantPkey() + " | " + StackTrace.getRootCause(e, getClass().getName()));
//        }
//
//        return map;
//    }
//
//    private LinkedHashMap<String, Integer> setAndSort(List<Object[]> objectList, List<String> monthList) {
//        LinkedHashMap<String, Integer> finalMap = new LinkedHashMap<>();
//
//        Map<String, Integer> map = new HashMap<>();
//        objectList.forEach(objects -> map.put(objects[0].toString(), Integer.valueOf(objects[1].toString())));
//
//        monthList.forEach(m -> {
//            if (map.containsKey(m)) {
//                finalMap.put(m, map.get(m));
//            } else {
//                finalMap.put(m, 0);
//            }
//        });
//        return finalMap;
//    }
}
