package com.baiye.controller;

import com.baiye.consumer.DatabaseExcuter;
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
        int numberOfCrawlers = 7;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);


        controller.addSeed(" http://music.163.com/");


        logger.info("Spider Started.");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        DatabaseExcuter databaseExcuter = new DatabaseExcuter();
        executorService.execute(databaseExcuter);
        controller.start(MusicCrawler.class,numberOfCrawlers);

    }
}
