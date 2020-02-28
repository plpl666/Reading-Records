package com.imooc.mapper;

import com.imooc.pojo.Recepts;
import com.imooc.pojo.vo.ReceptsVO;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceptsMapper extends MyMapper<Recepts> {

    /**
     * 查询用户手记
     * @param userId 用户编号
     * @return 用户手记信息集合
     */
    List<ReceptsVO> selectRecepts(@Param("userId") String userId);

    /**
     * 通过描述查询用户手记
     * @param desc 手记描述
     * @return 用户手记信息集合
     */
    List<ReceptsVO> selectReceptsByDesc(@Param("desc") String desc);
}