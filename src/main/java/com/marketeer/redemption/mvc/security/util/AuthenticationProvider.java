package com.marketeer.redemption.mvc.security.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Vivek on 12/4/17.
 */
public class AuthenticationProvider implements PasswordEncoder {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationProvider.class);

    @Override
    public String encode(CharSequence charSequence) {
        return EncryptionUtil.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence rawPass, String encPass) {
        logger.info("inside isPasswordValid: "+rawPass+" | "+encPass);
        if (encPass.toString().trim().equalsIgnoreCase(EncryptionUtil.encrypt(rawPass.toString()))) {
            return true;
        } else {
            return false;
        }
    }
}
