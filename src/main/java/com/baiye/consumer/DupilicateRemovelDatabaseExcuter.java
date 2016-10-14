package com.baiye.consumer;

import com.baiye.DAO.DupilicateRemovalDAO;
import com.baiye.entity.DuplicateRemoval;
import com.baiye.utils.SingleBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Baiye on 2016/10/14.
 */
public class DupilicateRemovelDatabaseExcuter implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(DupilicateRemovelDatabaseExcuter.class);

    private LinkedBlockingQueue<String> dupilicateRemovalInsertQueue = SingleBlockingQueue.getDupilicateRemovalInsertQueue();

    public void run() {
        DupilicateRemovalDAO dupilicateRemovalDAO = new DupilicateRemovalDAO();
        logger.info("DupilicateRemovelDatabaseExcuter started.");
        while(true)
        {
            try {
                String md5 = dupilicateRemovalInsertQueue.take();
                DuplicateRemoval duplicateRemoval = new DuplicateRemoval();
                duplicateRemoval.setMd5(md5);
                dupilicateRemovalDAO.insertDuplicateRemoval(duplicateRemoval);
            } catch (InterruptedException e) {
                logger.error("从队列中获取MD5并处理数据失败：" + e.getMessage() + e.getStackTrace());
            }
        }

    }
}
