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

    private static ConcurrentHashMap<String,String> authMap = new ConcurrentHashMap<String, String>();

    private static int num = 0;

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
       /* if(num % 1000 == 0)
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
        }*/
    }

    public static void main(String[] args) {


        MusicDAO musicDAO = new MusicDAO();
        Map tempMap = musicDAO.findDuplicateRemoval("dasfff");
        /*Music music = new Music();
        music.setName("name");
        music.setArtistName("artistName");
        music.setAlbumName("albumName");
        music.setCommentCount(BigInteger.valueOf(Long.parseLong("321")));
        music.setSongURL("http://fdsafdsa");
        musics.add(music);
        Music music2 = new Music();
        music2.setName("2name");
        music2.setArtistName("2artistName");
        music2.setAlbumName("2albumName");
        music2.setCommentCount(BigInteger.valueOf(Long.parseLong("2")));
        music2.setSongURL("2http://fdsafdsa");
        musics.add(music2);

        musicDAO.insertMusic(musics);*/
    }

}
