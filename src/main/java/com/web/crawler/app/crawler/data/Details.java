package com.web.crawler.app.crawler.data;

import java.io.Serializable;

/**
 * Created by sgoyal on 24/04/20.
 */
public class Details implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String page_title;
    private String page_link;
    private int image_count;

    public String getPage_title()
    {
        return page_title;
    }

    public void setPage_title(String page_title)
    {
        this.page_title = page_title;
    }

    public String getPage_link()
    {
        return page_link;
    }

    public void setPage_link(String page_link)
    {
        this.page_link = page_link;
    }

    public int getImage_count()
    {
        return image_count;
    }

    public void setImage_count(int image_count)
    {
        this.image_count = image_count;
    }
}
