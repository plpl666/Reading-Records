package com.imooc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.*;
import com.imooc.pojo.Users;
import com.imooc.pojo.UsersFans;
import com.imooc.pojo.vo.FansAndFollows;
import com.imooc.service.UserService;
import com.imooc.utils.PagedResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UsersFansMapper usersFansMapper;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Users userIsExist(Users user) {
        return usersMapper.selectOne(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addUser(Users user) {
        usersMapper.insertSelective(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public FansAndFollows getFansAndFollows(String userId) {
        FansAndFollows fansAndFollows = new FansAndFollows();
        UsersFans usersFans = new UsersFans();
        usersFans.setUserId(userId);
        fansAndFollows.setFansCounts(usersFansMapper.selectCount(usersFans));
        usersFans.setUserId(null);
        usersFans.setFanId(userId);
        fansAndFollows.setFollowsCounts(usersFansMapper.selectCount(usersFans));
        return fansAndFollows;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void editUser(Users user) {
       usersMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Users getUser(String userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<Users> getUserFollows(String userId, Integer page, Integer pageSize) {
        Page<Users> pages = PageHelper.startPage(page, pageSize);
        usersMapper.selectUserFollowsOrFans(null,userId);
        PageInfo<Users> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<Users> getUserFans(String userId, Integer page, Integer pageSize) {
        Page<Users> pages = PageHelper.startPage(page, pageSize);
        usersMapper.selectUserFollowsOrFans(userId,null);
        PageInfo<Users> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }
}
