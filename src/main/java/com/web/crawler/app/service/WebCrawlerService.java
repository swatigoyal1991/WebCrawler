package com.web.crawler.app.service;

import com.web.crawler.app.crawler.data.CrawlNode;
import com.web.crawler.app.crawler.data.UrlData;
import com.web.crawler.app.crawler.data.WebCrawlerResponse;
import com.web.crawler.app.crawler.data.UrlStatus;

import java.io.IOException;

/**
 * Created by sgoyal on 26/04/20.
 */
public interface WebCrawlerService
{
    public void crawlURL(CrawlNode crawlNode) throws IOException, InterruptedException;

    WebCrawlerResponse processUrlData(UrlData url) throws InterruptedException, IOException;

    UrlStatus getLinkStatus(String url);
}

