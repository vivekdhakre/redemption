package com.marketeer.redemption.mvc.controller;

import com.marketeer.redemption.mvc.entity.MerchantDo;
import com.marketeer.redemption.mvc.entity.SystemAuthDo;
import com.marketeer.redemption.mvc.security.util.EncryptionUtil;
import com.marketeer.redemption.mvc.service.MerchantService;
import com.marketeer.redemption.mvc.service.SystemAuthService;
import com.marketeer.redemption.util.Constants;
import com.marketeer.redemption.util.ResponseHead;
import com.marketeer.redemption.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Vivek on 18/4/17.
 */

@Controller
@SessionAttributes({"systemAuthDo","merchantDo","loginpagepath"})
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SystemAuthService systemAuthService;

    @Autowired
    private MerchantService merchantService;

    @RequestMapping({"/","/m","/m/{id}"})
    public String loginPage(@PathVariable("id") Optional<Long> optional,Map<String,Object> map, HttpSession session){
        SystemAuthDo systemAuthDo = (SystemAuthDo)session.getAttribute("systemAuthDo");
        MerchantDo merchantDo = (MerchantDo)session.getAttribute("merchantDo");

        if(systemAuthDo!=null && merchantDo!=null){
            return "redirect:/home";
        }else{
            if(optional.isPresent()){
                MerchantDo merchantDo1 = merchantService.getById(optional.get());
                if(merchantDo1!=null)
                    map.put("mname",merchantDo1.getMerchantName());
                    map.put("logo",merchantDo1.getMerchantLogo());
            }

            return "login";
        }

    }

    @RequestMapping("/home")
    public String home(HttpSession session){
        SystemAuthDo systemAuthDo = (SystemAuthDo)session.getAttribute("systemAuthDo");
        MerchantDo merchantDo = (MerchantDo)session.getAttribute("merchantDo");

        if(systemAuthDo!=null && merchantDo!=null){
            return "home";
        }else{
            return "redirect:/";
        }


    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestParam("uname") String userName, @RequestParam("passwd") String password, Map<String,Object> map, RedirectAttributes redirectAttributes){

        try{

            if(userName!=null && !"".equals(userName.trim()) && password!=null && !"".equals(password.trim())){
                SystemAuthDo systemAuthDo = systemAuthService.getByEmailId(userName.toLowerCase());
                if(systemAuthDo!=null){
                    if(systemAuthDo.getPassword().equals(EncryptionUtil.encrypt(password))){
                        if (Constants.ROLE_MAP.containsKey(systemAuthDo.getSystemRole().getAdvertRolePkey())) {
                            map.put("systemAuthDo", systemAuthDo);
                            map.put("merchantDo", merchantService.getBySystemAuthFkey(systemAuthDo.getAdvertAuthPkey()));
                            return "redirect:/home";
                        } else {
                            redirectAttributes.addFlashAttribute("responseHead", new ResponseHead(HttpStatus.FORBIDDEN.getReasonPhrase(), "Permisson Denied"));
                            return "redirect:/";
                        }
                    }else{
//                  Invalid Password
                        redirectAttributes.addFlashAttribute("responseHead", new ResponseHead(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Invalid Password"));
                        return "redirect:/";
                    }
                }else{
//               Invalid User Name
                    redirectAttributes.addFlashAttribute("responseHead", new ResponseHead(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Invalid User Name"));
                    return "redirect:/";
                }
            }else{
                redirectAttributes.addFlashAttribute("responseHead", new ResponseHead(HttpStatus.UNAUTHORIZED.getReasonPhrase(), ""));
                return "redirect:/";
            }



        }catch(Exception e){
            redirectAttributes.addFlashAttribute("resp","Internal Error");
            logger.error("u: "+userName+" | p: "+password+" | "+ StackTrace.getRootCause(e,getClass().getName()));
            return "redirect:/";
        }
    }


    @RequestMapping("/logout")
    public String logout(SessionStatus status, RedirectAttributes redirectAttributes, HttpSession session, Map<String,Object> map) {
        MerchantDo merchantDo = (MerchantDo)session.getAttribute("merchantDo");
        if(merchantDo!=null)map.put("logo",merchantDo.getMerchantLogo());
        status.setComplete();
        redirectAttributes.addFlashAttribute("resp", "Logout Successfully");
        return "redirect:/";
    }
}
