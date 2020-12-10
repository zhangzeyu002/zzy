package com.xxxx.crm.dao;

import com.xxxx.crm.Query.RoleQuery;
import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper  extends BaseMapper<Role,Integer> {
   List<Map<String,Object>> selectRole();
   Role selectRoleByName(RoleQuery roleQuery);
   Role selectRoleByName02(String userName);

}