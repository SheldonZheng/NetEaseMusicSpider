package com.baiye.spider;

import com.baiye.Utils.SpiderUtil;
import com.baiye.entity.Music;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Baiye on 2016/10/10.
 */
public class MyCrawler extends WebCrawler{

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");

    private final static List<Music> musics = new ArrayList<Music>();

    private final static SpiderUtil spiderUtil = new SpiderUtil();

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
            if(page.getParseData() instanceof HtmlParseData)
            {
                Music music = new Music();
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                String text = htmlParseData.getText();
                String html = htmlParseData.getHtml();

                Set<WebURL> links = htmlParseData.getOutgoingUrls();

                System.out.println("Text length: " + text.length());
                System.out.println("Html length: " + html.length());
                System.out.println("Number of outgoing links: " + links.size());


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

                /*int commentCountIndexStart = text.indexOf("评论共") + 3;
                int commentCountIndexEnd = text.indexOf("条评论");
                String commentCount = text.substring(commentCountIndexStart,commentCountIndexEnd);
                music.setCommentCount(commentCount);*/

                String commentCount = spiderUtil.getCommentCount(url);

                music.setSongURL(url);

                music.setCommentCount(commentCount);

                System.out.println(music.toString());

                musics.add(music);

                System.out.println(musics.size());

            }
        }
    }
}
