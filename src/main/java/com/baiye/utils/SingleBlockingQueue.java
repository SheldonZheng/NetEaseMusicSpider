package com.baiye.utils;

import com.baiye.entity.Music;
import edu.uci.ics.crawler4j.crawler.Page;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Baiye on 2016/10/11.
 */
public class SingleBlockingQueue {
    private static class QueueHolder
    {
        private static final LinkedBlockingQueue<Music> MusicDataBaseInsertQueue = new LinkedBlockingQueue<Music>(100000);

        private static final LinkedBlockingQueue<Page> PageWaitQueue = new LinkedBlockingQueue<Page>(100000);

        private static final LinkedBlockingQueue<Music> GetCommentQueue = new LinkedBlockingQueue<Music>(100000);

        private static final LinkedBlockingQueue<String> DupilicateRemovalInsertQueue = new LinkedBlockingQueue<String>(100000);
    }

    public static final LinkedBlockingQueue<Music> getMusicDatabaseInsertQueue()
    {
        return QueueHolder.MusicDataBaseInsertQueue;
    }

    public static final LinkedBlockingQueue<Page> getPageWaitQueue()
    {
        return QueueHolder.PageWaitQueue;
    }

    public static final LinkedBlockingQueue<Music> getCommentQueue()
    {
        return QueueHolder.GetCommentQueue;
    }

    public static final LinkedBlockingQueue<String> getDupilicateRemovalInsertQueue()
    {
        return QueueHolder.DupilicateRemovalInsertQueue;
    }

}
