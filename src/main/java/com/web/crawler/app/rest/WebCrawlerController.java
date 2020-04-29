package com.web.crawler.app.rest; /**
 * Created by sgoyal on 23/04/20.
 */

import com.web.crawler.app.crawler.data.UrlData;
import com.web.crawler.app.crawler.data.WebCrawlerResponse;
import com.web.crawler.app.crawler.data.UrlStatus;
import com.web.crawler.app.service.WebCrawlerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/webCrawler")
public class WebCrawlerController implements WebCrawlerApi
{
    private WebCrawlerServiceImpl webCrawlerService = new WebCrawlerServiceImpl();

    @PostMapping(consumes = "application/json", produces = "application/json")
    public WebCrawlerResponse processPage(@RequestBody UrlData url) throws IOException, InterruptedException
    {
        return webCrawlerService.processUrlData(url);
    }


    @RequestMapping(path = "/status")
    @GetMapping(produces = "application/json")
    public UrlStatus getRequestStatus(@RequestParam(value = "url", required = true) final String url) throws IOException, InterruptedException
    {
        return webCrawlerService.getLinkStatus(url);
    }
}




