package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.vo.FansAndFollows;
import com.imooc.utils.PagedResult;

public interface UserService {

    /**
     * 判断用户是否存在
     * @param user 用户对象
     * @return 用户对象
     */
    Users userIsExist(Users user);

    /**
     * 添加用户
     * @param user 用户对象
     */
    void addUser(Users user);

    /**
     * 获取用户粉丝/关注数
     * @param userId 用户编号
     * @return 粉丝/关注对象
     */
    FansAndFollows getFansAndFollows(String userId);

    /**
     * 修改用户信息
     * @param user 用户对象
     */
    void editUser(Users user);

    /**
     * 获取用户信息
     * @param userId 用户编号
     * @return 用户对象
     */
    Users getUser(String userId);

    /**
     * 获取用户的所有关注
     * @param userId 用户编号
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult 对象
     */
    PagedResult<Users> getUserFollows(String userId , Integer page, Integer pageSize);

    /**
     * 获取用户的所有粉丝
     * @param userId 用户编号
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult 对象
     */
    PagedResult<Users> getUserFans(String userId , Integer page, Integer pageSize);

}
