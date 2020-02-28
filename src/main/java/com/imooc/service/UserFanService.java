package com.imooc.service;

import com.imooc.pojo.UsersFans;

public interface UserFanService {

    /**
     * 查找用户粉丝关联
     * @param userFan 用户粉丝对象
     * @return 用户粉丝对象
     */
    UsersFans userIsFan(UsersFans userFan);

    /**
     * 添加用户粉丝关联
     * @param userFan 用户粉丝对象
     */
    void addUserFan(UsersFans userFan);

    /**
     * 删除用户粉丝关联
     * @param userFan 用户粉丝对象
     */
    void removeUserFan(UsersFans userFan);

}
