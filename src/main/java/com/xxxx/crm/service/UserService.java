package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.Query.UserQuery;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class UserService extends BaseService<User,Integer> {
    @Resource
    UserMapper userMapper;
public Map<String,Object> queryUserByArrays(UserQuery userQuery){
    Map<String ,Object> map=new HashMap<>();
    PageHelper.startPage(userQuery.getPage(),userQuery.getLimit());
    List<User> user = userMapper.queryByArrays(userQuery);
    PageInfo<User> pageInfo=new PageInfo();
    map.put("code",200);
    map.put("mag","查询成功");
    map.put("count",pageInfo.getTotal());
    map.put("data",pageInfo.getList());
    return  map;
}
}
