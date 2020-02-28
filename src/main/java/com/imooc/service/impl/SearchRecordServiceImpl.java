package com.imooc.service.impl;

import com.imooc.mapper.SearchRecordsMapper;
import com.imooc.pojo.SearchRecords;
import com.imooc.service.SearchRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("searchRecordService")
public class SearchRecordServiceImpl implements SearchRecordService {

    @Resource
    private SearchRecordsMapper searchRecordsMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addSearchRecord(SearchRecords searchRecord) {
        searchRecordsMapper.insert(searchRecord);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<String> getReceptHotRecord() {
        return searchRecordsMapper.selectReceptHotRecord();
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<String> getBookHotRecord() {
        return searchRecordsMapper.selectBookHotRecord();
    }
}
