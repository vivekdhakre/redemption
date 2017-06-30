//package com.marketeer.redemption.mvc.security;
//
//import com.marketeer.redemption.util.Constants;
//import com.marketeer.redemption.util.StackTrace;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Set;
//
///**
// * @author Vivek on 29/6/17.
// * @project redemption
// * @package com.marketeer.redemption.mvc.security
// */
//public class CustomSuccessHandler implements AuthenticationSuccessHandler,AuthenticationFailureHandler{
//
//    private static Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);
//
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        try {
//            Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//            logger.info("authorities: " + authorities);
//
//
//            HttpSession session = request.getSession();
//            boolean flag = false;
//            for (String authRole : authorities) {
//                if (Constants.ROLE_LIST.contains(authRole)) {
//                    flag = true;
//                    break;
//                }
//            }
//
//            if (flag) {
//                User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                session.setAttribute("uname", authUser.getUsername());
//                session.setAttribute("authorities", authentication.getAuthorities());
//                redirectStrategy.sendRedirect(request, response, "/home");
//
//            } else {
//                request.getSession().setAttribute("loginstatus", HttpStatus.UNAUTHORIZED.getReasonPhrase());
//                redirectStrategy.sendRedirect(request, response, "/");
//            }
//        }catch (Exception e){
//            logger.error(StackTrace.getRootCause(e,getClass().getName()));
//        }
//    }
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//
//        try{
//            logger.info("========================================\n"+e);
//            System.out.println(e);
//            request.getSession().setAttribute("loginstatus", e.getMessage());
//            redirectStrategy.sendRedirect(request, response, "/");
//        }catch (Exception e1){
//
//            StackTrace.getRootCause(e1,getClass().getName());
//            throw e1;
//        }
//
//
//    }
//
//
//}
