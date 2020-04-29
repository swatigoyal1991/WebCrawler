package com.web.crawler.app.crawler.data;

import com.web.crawler.app.crawler.data.Details;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgoyal on 24/04/20.
 */
public class WebCrawlerResponse implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int total_links;
    private int total_images;
    private List<Details> details;

    public int getTotal_links()
    {
        return total_links;
    }

    public void setTotal_links(int total_links)
    {
        this.total_links = total_links;
    }

    public int getTotal_images()
    {
        return total_images;
    }

    public void setTotal_images(int total_images)
    {
        this.total_images = total_images;
    }

    public List<Details> getDetails()
    {
        if(this.details == null)
        {
            this.details = new ArrayList();
        }
        return details;
    }

    public void setDetails(List<Details> details)
    {
        this.details = details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Web Crawler Response Info \n");

        sb.append("Total links: ").append(toIndentedString(total_links)).append("\n");
        sb.append("Total images: ").append(toIndentedString(total_images)).append("\n");
        for(Details detail : details)
        {
            sb.append("Link Details:");
            sb.append("Page title : ").append(toIndentedString(detail.getPage_title())).append("\n");
            sb.append("Page link : ").append(toIndentedString(detail.getPage_link())).append("\n");
            sb.append("Image Count: ").append(toIndentedString(detail.getImage_count())).append("\n");
        }
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(final java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
