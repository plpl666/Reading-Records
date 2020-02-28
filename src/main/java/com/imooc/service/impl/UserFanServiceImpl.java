package com.imooc.service.impl;

import com.imooc.mapper.UsersFansMapper;
import com.imooc.pojo.UsersFans;
import com.imooc.service.UserFanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userFanService")
public class UserFanServiceImpl implements UserFanService {

    @Resource
    private UsersFansMapper usersFansMapper;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public UsersFans userIsFan(UsersFans userFan) {
        return usersFansMapper.selectOne(userFan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addUserFan(UsersFans userFan) {
        usersFansMapper.insert(userFan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void removeUserFan(UsersFans userFan) {
        usersFansMapper.delete(userFan);
    }
}
