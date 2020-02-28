package com.imooc.service;

import com.imooc.pojo.SearchRecords;

import java.util.List;

public interface SearchRecordService {

    /**
     * 添加用户搜索记录
     * @param searchRecord 搜索记录对象
     */
    void addSearchRecord(SearchRecords searchRecord);

    /**
     * 获取手记热搜
     * @return 信息集合
     */
    List<String> getReceptHotRecord();

    /**
     * 获取图书热搜
     * @return 信息集合
     */
    List<String> getBookHotRecord();

}
