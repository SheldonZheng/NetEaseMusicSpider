package com.baiye.consumer;

import com.baiye.DAO.MusicDAO;
import com.baiye.entity.Music;
import com.baiye.utils.SingleBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Baiye on 2016/10/11.
 */
public class MusicDatabaseExcuter implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(MusicDatabaseExcuter.class);

    private static LinkedBlockingQueue<Music> musicDatabaseInsertQueue = SingleBlockingQueue.getMusicDatabaseInsertQueue();

    public void run() {
        logger.info("MusicDatabaseExcuter started.");
        MusicDAO musicDAO = new MusicDAO();
        while(true)
        {
            try {

                Music music = musicDatabaseInsertQueue.take();
                System.out.println(music.toString());
                musicDAO.insertMusic(music);
            } catch (InterruptedException e) {
                logger.error("从队列中获取Music待写入数据失败：" + e.getMessage() + e.getStackTrace());
            }
        }
    }
}
