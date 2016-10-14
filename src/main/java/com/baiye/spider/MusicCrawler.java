package com.baiye.spider;

import com.baiye.utils.AuthData;
import com.baiye.utils.SingleBlockingQueue;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

/**
 * Created by Baiye on 2016/10/10.
 */
public class MusicCrawler extends WebCrawler{

    private Logger logger = LoggerFactory.getLogger(MusicCrawler.class);


    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");



    //private LinkedBlockingQueue<Music> queue = SingleBlockingQueue.getMusicDatabaseInsertQueue();

    private LinkedBlockingQueue<Page> pageExcuterQueue = SingleBlockingQueue.getPageWaitQueue();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://music.163.com/");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL:" + url);

        if(url.indexOf("song?id") > 0)
        {
        if(AuthData.authDuplicateRemoval(url))
        {
            try {
                pageExcuterQueue.put(page);
            } catch (InterruptedException e) {
                logger.error("放Page数据入队列失败：" + e.getMessage() + e.getStackTrace());
            }

           /* if(page.getParseData() instanceof HtmlParseData)
            {

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

                String commentCount = spiderUtil.getCommentCount(url);

                music.setSongURL(url);
                if(commentCount != null)
                {
                    music.setCommentCount(BigInteger.valueOf(Long.parseLong(commentCount)));
                }
                else
                {
                    music.setCommentCount(BigInteger.valueOf(Long.parseLong("0")));
                }
                try {
                    if(music.isValid())
                        queue.put(music);
                } catch (InterruptedException e) {
                    logger.error("放数据入队列失败：" + e.getMessage());
                }


            }*/
        }

        }
    }
}
