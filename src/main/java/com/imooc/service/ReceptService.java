package com.imooc.service;

import com.imooc.pojo.Recepts;
import com.imooc.pojo.vo.ReceptsVO;
import com.imooc.utils.PagedResult;

public interface ReceptService {

    /**
     * 用户添加手记
     * @param recept 手记对象
     */
    void addRecept(Recepts recept);

    /**
     * 获取手记信息
     * @param userId 用户编号
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult对象
     */
    PagedResult<ReceptsVO> getRecepts(String userId, Integer page, Integer pageSize);

    /**
     * 修改手记浏览量
     * @param recept 手记对象
     */
    void editReceptFlow(Recepts recept);

    /**
     * 通过表述查询手记
     * @param desc 手记描述
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult 对象
     */
    PagedResult<ReceptsVO> getReceptsByDesc(String desc, Integer page, Integer pageSize);

}
