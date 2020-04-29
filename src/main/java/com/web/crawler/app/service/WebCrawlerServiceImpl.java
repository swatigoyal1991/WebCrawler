package com.web.crawler.app.service;

import com.web.crawler.app.crawler.data.*;
import com.web.crawler.app.crawler.data.WebCrawlerResponse;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sgoyal on 26/04/20.
 */

public class WebCrawlerServiceImpl implements WebCrawlerService
{
    private WebCrawlerResponse webCrawlerResponse;
    List<Details> detailsArrayList;
    private int imageCount = 0;
    private HashSet<String> links;
    private HashMap<String, UrlStatus> urlStatuses = new HashMap<String, UrlStatus>();
    private static HashSet<String> alreadyCrawledSet;
    private static List<String> deadLinks;
    private static String baseURL;
    private static LinkedBlockingQueue<CrawlNode> URLSToCrawl;

    @Cacheable(value = "cacheUrlData")
    public WebCrawlerResponse processUrlData(UrlData url) throws IOException
    {
        webCrawlerResponse = new WebCrawlerResponse();
        detailsArrayList = webCrawlerResponse.getDetails();
        links = new HashSet<String>();
        deadLinks = new LinkedList<String>();

        baseURL = url.getBaseUrl();
        urlStatuses.put(baseURL, UrlStatus.Submitted);

        alreadyCrawledSet = new HashSet<String>();
        URLSToCrawl = new LinkedBlockingQueue<CrawlNode>();

        alreadyCrawledSet.add(baseURL);
        URLSToCrawl.add(new CrawlNode(baseURL, 0));

        while (!URLSToCrawl.isEmpty())
        {
            try
            {
                CrawlNode urlToCrawl = URLSToCrawl.take();
                if (urlToCrawl.getLinkDepth() <= url.getDepth())
                {
                    links.add(urlToCrawl.getLink());
                    urlStatuses.put(urlToCrawl.getLink(), UrlStatus.InProcess);
                    crawlURL(urlToCrawl);
                }
                else
                {
                    break;
                }
            }
            catch (Exception e)
            {
                //
            }

        }
        webCrawlerResponse.setTotal_links(links.size());
        webCrawlerResponse.setTotal_images(imageCount);
        webCrawlerResponse.setDetails(detailsArrayList);

        urlStatuses.put(baseURL, UrlStatus.Processed);

        serializeWebCrawlerResponse(webCrawlerResponse);
        return webCrawlerResponse;
    }

    private void serializeWebCrawlerResponse(WebCrawlerResponse webCrawlerResponse)
    {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream("c:\\temp\\webCrawlerResponse.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(webCrawlerResponse);

            System.out.println("Done");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public UrlStatus getLinkStatus(String url)
    {
        return (!urlStatuses.isEmpty() && urlStatuses.containsKey(url) ? UrlStatus.fromValue(urlStatuses.get(url).getUrlCrawlStatus()) : UrlStatus.NotProcessed);
    }

    public void crawlURL(CrawlNode crawlNode) throws IOException {
        System.out.println("Depth " + crawlNode.getLinkDepth() + "  " + crawlNode.getLink());
        int updatedDepth = crawlNode.getLinkDepth();
        try {
            Document doc = Jsoup.connect(crawlNode.getLink()).get();

            Elements links = doc.select("a[href]");
            Elements imagesOnPage = doc.select("img[src]");

            Details details = new Details();
            details.setPage_link(crawlNode.getLink());
            details.setPage_title(doc.title());
            details.setImage_count(imagesOnPage.size());
            detailsArrayList.add(details);

            urlStatuses.put(crawlNode.getLink(),UrlStatus.Processed);

            imageCount += imagesOnPage.size();

            if(!links.isEmpty()){
                updatedDepth = updatedDepth+1;
            }
            for (Element link : links) {
                String linkURL = link.attr("abs:href");
                if (!alreadyCrawled(linkURL)) {
                    alreadyCrawledSet.add(linkURL);
                    URLSToCrawl.put(new CrawlNode(linkURL, updatedDepth));
                }
            }

        } catch (HttpStatusException e) {
            deadLinks.add(crawlNode.getLink());
            urlStatuses.put(crawlNode.getLink(), UrlStatus.Failed);
        }
        catch (Exception e)
        {
            deadLinks.add(crawlNode.getLink());
            urlStatuses.put(crawlNode.getLink(), UrlStatus.Failed);
        }
    }

    private static boolean alreadyCrawled(String url) {
        if (alreadyCrawledSet.contains(url)) {
            return true;
        } else {
            return false;
        }
    }
}
