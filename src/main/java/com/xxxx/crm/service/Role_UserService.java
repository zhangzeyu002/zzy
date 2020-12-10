package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.User_RoleMapper;
import com.xxxx.crm.vo.User_Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class Role_UserService extends BaseService<User_Role,Integer> {
    @Resource
    User_RoleMapper user_roleMapper;
    public void insertRole_User(Integer userId,String roleIds){
        if(StringUtils.isBlank(roleIds)){
            String[] a=roleIds.split(",");
            User_Role user_role=new User_Role();

        }
    }
}
