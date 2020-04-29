package com.web.crawler.app.crawler.data;

/**
 * Created by sgoyal on 23/04/20.
 */
public class UrlData
{

    public UrlData() {
        this.baseUrl = baseUrl;
        this.depth = depth;
    }

    private String baseUrl;
    private int depth;

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }
}
