package com.baiye.utils;

import com.baiye.entity.Music;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Baiye on 2016/10/11.
 */
public class SingleBlockingQueue {
    private static class QueueHolder
    {
        private static final LinkedBlockingQueue<Music> INSTANCE = new LinkedBlockingQueue<Music>(100000);
    }

    public static final LinkedBlockingQueue<Music> getInstance()
    {
        return QueueHolder.INSTANCE;
    }
}
