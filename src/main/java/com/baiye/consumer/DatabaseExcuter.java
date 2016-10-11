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
public class DatabaseExcuter implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(DatabaseExcuter.class);

    private static LinkedBlockingQueue<Music> queue = SingleBlockingQueue.getInstance();

    public void run() {
        logger.info("DatabaseExcuter started.");
        MusicDAO musicDAO = new MusicDAO();
        while(true)
        {
            try {

                Music music = queue.take();

                System.out.println(music.toString());
                musicDAO.insertMusic(music);
            } catch (InterruptedException e) {
                logger.error("从队列中获取数据失败：" + e.getMessage());
            }
        }
    }
}
