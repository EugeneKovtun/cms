package ua.kpi.tef.cms.dao.dto;


import ua.kpi.tef.cms.entities.ContainerType;
import ua.kpi.tef.cms.entities.OrderType;
import ua.kpi.tef.cms.entities.Page;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

// TODO: 07.04.19 refactor Page Structure
@Entity
@Table(name = "page")
public class PageDto implements Comparable<PageDto>, Comparator<PageDto> {
    @Id
    private String id;
    private String title;
    private String titleUa;
    private String caption;
    private String captionUa;
    private String content;
    private String contentUa;
    private String intro;
    private String introUa;
    private LocalDateTime dateTime;
    private Integer orderNum;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    @Enumerated(EnumType.STRING)
    private ContainerType containerType;

    private String linkToImage;
    private String linkToImageUa;
    @ManyToOne
    private PageDto aliasOf;
    @ManyToOne
    private PageDto parentPage;

    public String getId() {
        return id;
    }

    public PageDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PageDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitleUa() {
        return titleUa;
    }

    public PageDto setTitleUa(String titleUa) {
        this.titleUa = titleUa;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public PageDto setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getCaptionUa() {
        return captionUa;
    }

    public PageDto setCaptionUa(String captionUa) {
        this.captionUa = captionUa;
        return this;
    }

    public String getContent() {
        return content;
    }

    public String resolveContent(String locale) {
        if ("ua".equals(locale)) {
            return contentUa;
        }

        return content;
    }

    public PageDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getContentUa() {
        return contentUa;
    }

    public PageDto setContentUa(String contentUa) {
        this.contentUa = contentUa;
        return this;
    }

    public String getIntro() {
        return intro;
    }

    public PageDto setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public String getIntroUa() {
        return introUa;
    }

    public PageDto setIntroUa(String introUa) {
        this.introUa = introUa;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public PageDto setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public PageDto setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public PageDto setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public PageDto setContainerType(ContainerType containerType) {
        this.containerType = containerType;
        return this;
    }

    public PageDto getAliasOf() {
        return aliasOf;
    }

    public PageDto setAliasOf(PageDto aliasOf) {
        this.aliasOf = aliasOf;
        return this;
    }

    public PageDto getParentPage() {
        return parentPage;
    }

    public PageDto setParentPage(PageDto parentPage) {
        this.parentPage = parentPage;
        return this;
    }

    public String getLinkToImage() {
        return linkToImage;
    }

    public PageDto setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
        return this;
    }

    public String getLinkToImageUa() {
        return linkToImageUa;
    }

    public PageDto setLinkToImageUa(String linkToImageUa) {
        this.linkToImageUa = linkToImageUa;
        return this;
    }


    public Page resolvePage(String locale) {
        Page page = new Page()
                .setId(id)
                .setParentPageId(parentPage == null ? null : parentPage.getId())
                .setDateTime(dateTime)
                .setContainerType(containerType);
        if ("ua".equals(locale)) {
            page.setContent(contentUa)
                    .setTitle(titleUa)
                    .setIntro(introUa)
                    .setCaption(captionUa)
                    .setLinkToImage(linkToImageUa);
        } else {
            page.setContent(content)
                    .setTitle(title)
                    .setIntro(intro)
                    .setCaption(caption)
                    .setLinkToImage(linkToImage);
        }
        return page;
    }

    @Override
    public int compare(PageDto o1, PageDto o2) {
        if (orderType == OrderType.ORDER_NUM) {
            return o1.getOrderNum().compareTo(o2.getOrderNum());
        }
        if (orderType == OrderType.DATE) {
            return o1.getDateTime().compareTo(o2.getDateTime());
        }
        return o1.getId().compareTo(o2.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageDto pageDto = (PageDto) o;
        return Objects.equals(id, pageDto.id) &&
                Objects.equals(title, pageDto.title) &&
                Objects.equals(titleUa, pageDto.titleUa) &&
                Objects.equals(caption, pageDto.caption) &&
                Objects.equals(captionUa, pageDto.captionUa) &&
                Objects.equals(content, pageDto.content) &&
                Objects.equals(contentUa, pageDto.contentUa) &&
                Objects.equals(intro, pageDto.intro) &&
                Objects.equals(introUa, pageDto.introUa) &&
                Objects.equals(dateTime, pageDto.dateTime) &&
                Objects.equals(orderNum, pageDto.orderNum) &&
                orderType == pageDto.orderType &&
                containerType == pageDto.containerType &&
                Objects.equals(linkToImage, pageDto.linkToImage) &&
                Objects.equals(linkToImageUa, pageDto.linkToImageUa) &&
                Objects.equals(aliasOf, pageDto.aliasOf) &&
                Objects.equals(parentPage, pageDto.parentPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, titleUa, caption, captionUa, content, contentUa, intro, introUa, dateTime, orderNum, orderType, containerType, linkToImage, linkToImageUa, aliasOf, parentPage);
    }

    @Override
    public int compareTo(PageDto pageToCompare) {
        if (orderType == OrderType.ORDER_NUM) {
            return orderNum.compareTo(pageToCompare.orderNum);
        }
        if (orderType == OrderType.DATE) {
            return dateTime.compareTo(pageToCompare.getDateTime());
        }
        return id.compareTo(pageToCompare.getId());
    }
}
