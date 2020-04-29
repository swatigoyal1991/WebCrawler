package com.web.crawler.app.crawler.data;

/**
 * Created by sgoyal on 26/04/20.
 */
public enum UrlStatus
{
    Submitted("Submitted"),
    InProcess("InProcess"),
    Processed("Processed"),
    NotProcessed("Not Processed"),
    Failed("Failed");

    private final String urlCrawlStatus;


    UrlStatus(String urlCrawlStatus)
    {
        this.urlCrawlStatus = urlCrawlStatus;
    }

    public String getUrlCrawlStatus()
    {
        return urlCrawlStatus;
    }

    public static UrlStatus fromValue(String val)
    {
        val = (val == null ? null : val.trim());
        for (UrlStatus urlStatus : UrlStatus.values())
        {
            if(urlStatus.getUrlCrawlStatus().equalsIgnoreCase(val))
            {
                return urlStatus;
            }
        }
        return UrlStatus.Failed;
    }


}
