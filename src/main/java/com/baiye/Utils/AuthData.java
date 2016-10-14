package com.baiye.utils;

import com.baiye.DAO.MusicDAO;
import com.baiye.entity.DuplicateRemoval;
import com.baiye.entity.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Baiye on 2016/10/10.
 */
public class AuthData {

    private static Logger logger = LoggerFactory.getLogger(AuthData.class);

    private static MusicDAO musicDAO = new MusicDAO();

    public static boolean authData(String source)
    {


        String md5 = MD5Util.getMD5(source.getBytes());
        Map tempMap = musicDAO.findDuplicateRemoval(md5);
        if(tempMap != null)
            return false;
        else
        {
            DuplicateRemoval duplicateRemoval = new DuplicateRemoval();
            duplicateRemoval.setMd5(md5);
            musicDAO.insertDuplicateRemoval(duplicateRemoval);
            return true;
        }

    }


}
