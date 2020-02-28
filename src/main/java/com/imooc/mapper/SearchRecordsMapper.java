package com.imooc.mapper;

import com.imooc.pojo.SearchRecords;
import com.imooc.utils.MyMapper;

import java.util.List;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {

    /**
     * 查询手记热搜词
     * @return 热搜词集合
     */
    List<String> selectReceptHotRecord();

    /**
     * 查询图书热搜词
     * @return 热搜词集合
     */
    List<String> selectBookHotRecord();

}