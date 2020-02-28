package com.imooc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.ReceptsMapper;
import com.imooc.pojo.Recepts;
import com.imooc.pojo.vo.ReceptsVO;
import com.imooc.service.ReceptService;
import com.imooc.utils.PagedResult;
import com.imooc.utils.TimeAgoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("receptService")
public class ReceptServiceImpl implements ReceptService {

    @Resource
    private ReceptsMapper receptsMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addRecept(Recepts recept) {
        receptsMapper.insert(recept);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<ReceptsVO> getRecepts(String userId, Integer page, Integer pageSize) {
        Page<ReceptsVO> pages = PageHelper.startPage(page,pageSize);
        List<ReceptsVO> receptsVOList = receptsMapper.selectRecepts(userId);
        for (ReceptsVO r : receptsVOList) {
            r.setTimeAgo(TimeAgoUtils.format(r.getCreateTime()));
        }
        PageInfo<ReceptsVO> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void editReceptFlow(Recepts recept) {
        receptsMapper.updateByPrimaryKeySelective(recept);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<ReceptsVO> getReceptsByDesc(String desc, Integer page, Integer pageSize) {
        Page<ReceptsVO> pages = PageHelper.startPage(page,pageSize);
        List<ReceptsVO> receptsVOList = receptsMapper.selectReceptsByDesc(desc);
        for (ReceptsVO r : receptsVOList) {
            r.setTimeAgo(TimeAgoUtils.format(r.getCreateTime()));
        }
        PageInfo<ReceptsVO> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }
}
