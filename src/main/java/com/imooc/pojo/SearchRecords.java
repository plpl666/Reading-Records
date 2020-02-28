package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "search_records")
public class SearchRecords {
    /**
     * 搜索记录编号
     */
    @Id
    private String id;

    /**
     * 搜索图书记录
     */
    @Column(name = "book_record")
    private String bookRecord;

    /**
     * 搜索感受记录
     */
    @Column(name = "recept_record")
    private String receptRecord;

    /**
     * 获取搜索记录编号
     *
     * @return id - 搜索记录编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置搜索记录编号
     *
     * @param id 搜索记录编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取搜索图书记录
     *
     * @return book_record - 搜索图书记录
     */
    public String getBookRecord() {
        return bookRecord;
    }

    /**
     * 设置搜索图书记录
     *
     * @param bookRecord 搜索图书记录
     */
    public void setBookRecord(String bookRecord) {
        this.bookRecord = bookRecord;
    }

    /**
     * 获取搜索感受记录
     *
     * @return recept_record - 搜索感受记录
     */
    public String getReceptRecord() {
        return receptRecord;
    }

    /**
     * 设置搜索感受记录
     *
     * @param receptRecord 搜索感受记录
     */
    public void setReceptRecord(String receptRecord) {
        this.receptRecord = receptRecord;
    }
}