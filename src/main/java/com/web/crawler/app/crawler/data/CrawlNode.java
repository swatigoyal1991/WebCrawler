package com.web.crawler.app.crawler.data;

/**
 * Created by sgoyal on 25/04/20.
 */
public class CrawlNode
{
    private String link;
    private int linkDepth;

    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CrawlNode crawlNode = (CrawlNode) o;

        if (linkDepth != crawlNode.linkDepth)
            return false;
        return !(link != null ? !link.equals(crawlNode.link) : crawlNode.link != null);

    }

    @Override public int hashCode()
    {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + linkDepth;
        return result;
    }

    public CrawlNode(String link, int linkDepth)
    {
        this.link = link;
        this.linkDepth = linkDepth;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public int getLinkDepth()
    {
        return linkDepth;
    }

    public void setLinkDepth(int linkDepth)
    {
        this.linkDepth = linkDepth;
    }
}
