package com.baiye.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Baiye on 2016/10/10.
 */
public class AuthData {

    private static Logger logger = LoggerFactory.getLogger(AuthData.class);

    private static ConcurrentHashMap<String,String> authMap = new ConcurrentHashMap<String, String>();

    private static int num = 0;

    public static boolean authData(String source)
    {
        num++;
        String md5 = MD5Util.getMD5(source.getBytes());
        if(num % 1000 == 0)
        {


        }
        if(authMap.get(md5) != null && !authMap.get(md5).equals("1"))
        {
            return false;
        }
        else
        {
            authMap.put(md5,"1");
            return true;
        }
    }


}
