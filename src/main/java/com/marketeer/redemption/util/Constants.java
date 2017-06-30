package com.marketeer.redemption.util;


import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


public class Constants {


    public static Map<Long, String> ROLE_MAP;





    static {

        try {

            ROLE_MAP = new LinkedHashMap<>();
            ROLE_MAP.put(5l, "ROLE_TRIAL");
            ROLE_MAP.put(6l, "ROLE_SILVER");
            ROLE_MAP.put(7l, "ROLE_GOLD");
            ROLE_MAP.put(8l, "ROLE_PLATINUM");
            ROLE_MAP.put(9l, "ROLE_ENTERPRISE");
            ROLE_MAP.put(10l, "ROLE_FREE");



        } catch (Exception e) {
        }
    }


}