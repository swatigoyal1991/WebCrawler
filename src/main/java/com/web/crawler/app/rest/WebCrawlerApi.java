package com.web.crawler.app.rest;

import com.web.crawler.app.crawler.data.UrlData;
import com.web.crawler.app.crawler.data.WebCrawlerResponse;
import com.web.crawler.app.crawler.data.UrlStatus;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * Created by sgoyal on 26/04/20.
 */
public interface WebCrawlerApi
{
    WebCrawlerResponse processPage(UrlData url) throws IOException, InterruptedException;
    UrlStatus getRequestStatus(@RequestParam String url) throws IOException, InterruptedException;
}
