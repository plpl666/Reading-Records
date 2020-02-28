package com.imooc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

public class Books {
    /**
     * 编号
     */
    @Id
    private String id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publishing;

    /**
     * 简介
     */
    private String gist;

    /**
     * 图片路径
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 发行时间
     */
    @Column(name = "sell_time")
    private String sellTime;

    /**
     * ISBN码
     */
    private String isbn;

    /**
     * 进度：-1.读完 num.读到第几页
     */
    private Integer schedule;

    /**
     * 状态：1.分享 0.未分享
     */
    private Integer status;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 分享时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "up_time")
    private Date upTime;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取书名
     *
     * @return name - 书名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置书名
     *
     * @param name 书名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取出版社
     *
     * @return publishing - 出版社
     */
    public String getPublishing() {
        return publishing;
    }

    /**
     * 设置出版社
     *
     * @param publishing 出版社
     */
    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    /**
     * 获取简介
     *
     * @return gist - 简介
     */
    public String getGist() {
        return gist;
    }

    /**
     * 设置简介
     *
     * @param gist 简介
     */
    public void setGist(String gist) {
        this.gist = gist;
    }

    /**
     * 获取图片路径
     *
     * @return img_url - 图片路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片路径
     *
     * @param imgUrl 图片路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取发行时间
     *
     * @return sell_time - 发行时间
     */
    public String getSellTime() {
        return sellTime;
    }

    /**
     * 设置发行时间
     *
     * @param sellTime 发行时间
     */
    public void setSellTime(String sellTime) {
        this.sellTime = sellTime;
    }

    /**
     * 获取ISBN码
     *
     * @return isbn - ISBN码
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * 设置ISBN码
     *
     * @param isbn ISBN码
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 获取进度：-1.读完 0.闲置 num.读到第几页
     *
     * @return schedule - 进度：-1.读完 0.闲置 num.读到第几页
     */
    public Integer getSchedule() {
        return schedule;
    }

    /**
     * 设置进度：-1.读完 0.闲置 num.读到第几页
     *
     * @param schedule 进度：-1.读完 0.闲置 num.读到第几页
     */
    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    /**
     * 获取状态：1.分享 0.未分享
     *
     * @return status - 状态：1.分享 0.未分享
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：1.分享 0.未分享
     *
     * @param status 状态：1.分享 0.未分享
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取分享时间
     *
     * @return up_time - 分享时间
     */
    public Date getUpTime() {
        return upTime;
    }

    /**
     * 设置分享时间
     *
     * @param upTime 分享时间
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}