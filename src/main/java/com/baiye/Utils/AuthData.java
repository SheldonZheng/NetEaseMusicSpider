package com.baiye.utils;

import com.baiye.DAO.DupilicateRemovalDAO;
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
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Baiye on 2016/10/10.
 */
public class AuthData {

    private static Logger logger = LoggerFactory.getLogger(AuthData.class);

    private static Map<String,Integer> tempMap = new ConcurrentHashMap<String, Integer>();

    private static LinkedBlockingQueue<String> dupilicateRemovalInsertQueue = SingleBlockingQueue.getDupilicateRemovalInsertQueue();

    private static DupilicateRemovalDAO dupilicateRemovalDAO = new DupilicateRemovalDAO();

    public static boolean authDuplicateRemoval(String source)
    {

        String md5 = MD5Util.getMD5(source.getBytes());
        Map tempMap = dupilicateRemovalDAO.findDuplicateRemoval(md5);
        if(tempMap != null)
            return false;
        else
        {
            try {
                dupilicateRemovalInsertQueue.put(md5);
            } catch (InterruptedException e) {
                logger.error("向队列中放入MD5数据失败：" + e.getMessage() + e.getStackTrace());
            }
            return true;
        }

    }

    public static boolean authRemovalMusic(Music music)
    {
        String str = music.toString();
        String md5 = MD5Util.getMD5(str.getBytes());
        if(tempMap.get(md5) != null && tempMap.get(md5) < 10)
        {
            int count = tempMap.get(md5) + 1;
            if(count >= 10)
                return false;
            tempMap.put(md5,count);
            return true;
        }
        else
        {
            if(tempMap.get(md5) != null)
            {
                int count = tempMap.get(md5) + 1;
                if(count >= 10)
                    return false;
                tempMap.put(md5,count);
                return true;
            }
            else
            {
                tempMap.put(md5,1);
                return true;
            }
        }
    }

/*
    public static void main(String[] args) {

        Music music = new Music();
        music.setName("1");
        music.setCommentCount(new BigInteger("2"));
        music.setAlbumName("3");
        music.setSongURL("xczc");
        music.setArtistName("cccc");

        for(int i = 0;i < 12;i++)
        {
            System.out.println(authRemovalMusic(music));
        }


    }*/


}
