package com.baiye.consumer;

import com.baiye.entity.Music;
import com.baiye.utils.SingleBlockingQueue;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Baiye on 2016/10/14.
 */
public class PageExcuter implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(PageExcuter.class);

    private LinkedBlockingQueue<Page> pageExcuteQueue = SingleBlockingQueue.getPageWaitQueue();

    private LinkedBlockingQueue<Music> musicGetCommentCountQueue = SingleBlockingQueue.getCommentQueue();

    public void run() {
        logger.info("PageExcuter started.");
        while(true)
        {
            try {
                Page page = pageExcuteQueue.take();
                if(page.getParseData() instanceof HtmlParseData)
                {
                    String url = page.getWebURL().getURL();

                    Music music = new Music();
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    String text = htmlParseData.getText();

                    int musicNameIndexStart = text.indexOf("生成外链播放器") + 8;
                    int musicNameIndexEnd = text.indexOf("歌手");
                    String musicName = text.substring(musicNameIndexStart,musicNameIndexEnd).trim();
                    music.setName(musicName);

                    int artistNameIndexStart = text.indexOf("歌手：") + 3;
                    int artistNameIndexEnd = text.indexOf("所属专辑：");
                    String artistName = text.substring(artistNameIndexStart,artistNameIndexEnd).trim();
                    music.setArtistName(artistName);

                    int albumNameIndexStart = text.indexOf("所属专辑：") + 5;
                    int albumNameIndexEnd = text.indexOf("播放",albumNameIndexStart);
                    String albumName = text.substring(albumNameIndexStart,albumNameIndexEnd).trim();
                    music.setAlbumName(albumName);

                    music.setSongURL(url);

                    try {
                        if(music.isValid())
                            musicGetCommentCountQueue.put(music);
                    } catch (InterruptedException e) {
                        logger.error("放Music数据入队列失败：" + e.getMessage() + e.getStackTrace());
                    }



/*

                    String commentCount = spiderUtil.getCommentCount(url);
*/


                    /*if(commentCount != null)
                    {
                        music.setCommentCount(BigInteger.valueOf(Long.parseLong(commentCount)));
                    }
                    else
                    {
                        music.setCommentCount(BigInteger.valueOf(Long.parseLong("0")));
                    }*/
                    /*try {
                        if(music.isValid())
                            musicExcuteQueue.put(music);
                    } catch (InterruptedException e) {
                        logger.error("放Music数据入队列失败：" + e.getMessage() + e.getStackTrace());
                    }*/


                }
            } catch (InterruptedException e) {
                logger.error("从队列中获取Page并处理数据失败：" + e.getMessage() + e.getStackTrace());
            }
        }

    }
}
