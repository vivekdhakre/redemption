package com.marketeer.redemption.mvc.controller;


import com.marketeer.redemption.mvc.entity.MerchantDo;
import com.marketeer.redemption.mvc.entity.SystemAuthDo;
import com.marketeer.redemption.mvc.service.CouponValueService;
import com.marketeer.redemption.util.Header;
import com.marketeer.redemption.util.Response;
import com.marketeer.redemption.util.ResponseHead;

import com.marketeer.redemption.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by ahoy on 26/4/17.
 */

@Controller
public class CouponController {

    private static Logger logger = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponValueService couponValueService;

    @RequestMapping(value = "/couponinfo")
    public String getCouponDetail(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(value = "coupon", required = false) String coupon, Map<String, Object> map) {

        SystemAuthDo systemAuthDo = (SystemAuthDo) session.getAttribute("systemAuthDo");
        MerchantDo merchantDo = (MerchantDo) session.getAttribute("merchantDo");

        if (systemAuthDo != null && merchantDo != null) {
            Response response = null;

            if(coupon==null){
                coupon = (String)session.getAttribute("coupon");
                session.removeAttribute("coupon");
            }

            if (coupon != null && !"".equals(coupon.trim())) {
                response = couponValueService.getInfo(coupon.toUpperCase(), merchantDo.getMerchantPkey());
                map.put("coupon", coupon.toUpperCase());

            } else {
                response = new Response(new Header(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
                map.put("coupon", coupon);
            }
            map.put("heading", "Coupon Details");
            map.put("couponResponse", response);
            return "home";
        } else {
            redirectAttributes.addFlashAttribute("responseHead", new ResponseHead(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Session Expired or Invalid"));
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/redeem", method = RequestMethod.POST)
    public String redeemCoupon(@RequestParam("couponvalueid") long coupoValueId, @RequestParam("coupon") String coupon, @RequestParam("branchid") long branchId, HttpSession session, RedirectAttributes redirectAttributes) {
        String resp = null;
        try {
            SystemAuthDo systemAuthDo = (SystemAuthDo) session.getAttribute("systemAuthDo");
            MerchantDo merchantDo = (MerchantDo) session.getAttribute("merchantDo");

            if (systemAuthDo != null && merchantDo != null) {

                session.setAttribute("coupon",coupon);

                resp = couponValueService.redeemCoupon(coupoValueId, branchId, merchantDo.getMerchantPkey());
                redirectAttributes.addFlashAttribute("responseHead", new ResponseHead("Redemption Status", resp));

            } else {
                redirectAttributes.addFlashAttribute("responseHead", new ResponseHead(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Session Expired or Invalid"));
                return "redirect:/";
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("responseHead", new ResponseHead("Error!", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
            logger.error("couponValueId: " + coupoValueId + " | branchId: " + branchId + " | " + StackTrace.getRootCause(e, getClass().getName()));
        } finally {
            logger.info("couponValueId: " + coupoValueId + " | branchId: " + branchId + " | resp: " + resp);
            redirectAttributes.addFlashAttribute("resp", resp);
        }

        return "redirect:/couponinfo";
    }
}
