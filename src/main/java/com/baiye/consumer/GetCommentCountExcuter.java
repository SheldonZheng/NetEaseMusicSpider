package com.baiye.consumer;

import com.baiye.entity.Music;
import com.baiye.utils.AuthData;
import com.baiye.utils.SingleBlockingQueue;
import com.baiye.utils.GetCommentCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Baiye on 2016/10/14.
 */
public class GetCommentCountExcuter implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(GetCommentCountExcuter.class);

    private LinkedBlockingQueue<Music> musicGetCommentCountQueue = SingleBlockingQueue.getCommentQueue();

    private static LinkedBlockingQueue<Music> musicDatabaseInsertQueue = SingleBlockingQueue.getMusicDatabaseInsertQueue();

    public void run() {
        GetCommentCountUtil getCommentCountUtil = new GetCommentCountUtil();
        logger.info("GetCommentCountExcuter started.");
        while(true)
        {
            try {
                Music music = musicGetCommentCountQueue.take();

               if(AuthData.authRemovalMusic(music))
               {
                   String commentCount = null;
                   try {
                       commentCount = getCommentCountUtil.getCommentCount(music.getSongURL());
                   } catch (Exception e) {
                       logger.error("获取Music评论数量数据失败：" + e.getMessage() + e.getStackTrace());
                       musicGetCommentCountQueue.put(music);
                       continue;
                   }

                   if(commentCount != null)
                   {
                       music.setCommentCount(BigInteger.valueOf(Long.parseLong(commentCount)));
                   }
                   else
                   {
                       music.setCommentCount(BigInteger.valueOf(Long.parseLong("0")));
                   }

                   musicDatabaseInsertQueue.put(music);
               }
            } catch (InterruptedException e) {
                logger.error("从队列中获取Music待获取评论数量数据失败：" + e.getMessage() + e.getStackTrace());
            }
        }

    }
}

