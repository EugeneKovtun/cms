package ua.kpi.tef.cms.entities;

import java.time.LocalDateTime;

public class Page {
    private String id;
    private String title;
    private String caption;
    private String content;
    private String intro;
    private LocalDateTime dateTime;
    private String linkToImage;
    private int orderNum;
    private OrderType orderType;
    private ContainerType containerType;
    private String parentPageId;

    public String getId() {
        return id;
    }

    public Page setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Page setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Page setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Page setContent(String content) {
        this.content = content;
        return this;
    }

    public String getIntro() {
        return intro;
    }

    public Page setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Page setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getLinkToImage() {
        return linkToImage;
    }

    public Page setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
        return this;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public Page setOrderNum(int orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Page setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public Page setContainerType(ContainerType containerType) {
        this.containerType = containerType;
        return this;
    }

    public String getParentPageId() {
        return parentPageId;
    }

    public Page setParentPageId(String parentPageId) {
        this.parentPageId = parentPageId;
        return this;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", caption='" + caption + '\'' +
                ", content='" + content + '\'' +
                ", intro='" + intro + '\'' +
                ", dateTime=" + dateTime +
                ", linkToImage='" + linkToImage + '\'' +
                '}';
    }
}
