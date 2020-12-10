package com.xxxx.crm.dao;

import com.xxxx.crm.Query.UserQuery;
import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User,Integer> {
    List<User> queryByArrays(UserQuery query);
}