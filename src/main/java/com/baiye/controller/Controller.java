package com.baiye.controller;

import com.baiye.consumer.DupilicateRemovelDatabaseExcuter;
import com.baiye.consumer.GetCommentCountExcuter;
import com.baiye.consumer.MusicDatabaseExcuter;
import com.baiye.consumer.PageExcuter;
import com.baiye.spider.MusicCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Baiye on 2016/10/10.
 */
public class Controller {


    public static void main(String[] args) throws Exception {

        Logger logger = LoggerFactory.getLogger(Controller.class);
        String crawlStorageFolder = args[0];
        int numberOfCrawlers = Integer.valueOf(args[1]);
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(Integer.valueOf(args[2]));

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);


        controller.addSeed(" http://music.163.com/");


        logger.info("Spider Started.");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        MusicDatabaseExcuter musicDatabaseExcuter = new MusicDatabaseExcuter();
        DupilicateRemovelDatabaseExcuter dupilicateRemovelDatabaseExcuter = new DupilicateRemovelDatabaseExcuter();
        GetCommentCountExcuter getCommentCountExcuter = new GetCommentCountExcuter();
        PageExcuter pageExcuter = new PageExcuter();
        executorService.execute(musicDatabaseExcuter);
        executorService.execute(dupilicateRemovelDatabaseExcuter);
        executorService.execute(getCommentCountExcuter);
        executorService.execute(pageExcuter);
        controller.start(MusicCrawler.class,numberOfCrawlers);

    }
}
