package com.imooc.mapper;

import com.imooc.pojo.Users;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper extends MyMapper<Users> {

    /**
     * 查询用户的关注或者粉丝
     * @param userId 用户编号
     * @param fanId 粉丝编号
     * @return 关注或粉丝集合
     */
    List<Users> selectUserFollowsOrFans(@Param("userId") String userId, @Param("fanId") String fanId);

}