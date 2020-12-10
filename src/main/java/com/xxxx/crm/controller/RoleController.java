package com.xxxx.crm.controller;

import com.xxxx.crm.Query.RoleQuery;
import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.service.RoleService;
import com.xxxx.crm.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Resource
    RoleService roleService;
    @ResponseBody
    @RequestMapping("selectRole")
    public List<Map<String,Object>> selectRoleController()
    {
        return roleService.selectRoleservice();
    }
    @RequestMapping("selectByName")
    @ResponseBody
    public Role selectByRoleNameController( RoleQuery roleQuery){
        return roleService.selectByRoleNameService(roleQuery);
    };
    @RequestMapping("insertRole")
    @ResponseBody
    public void InsertRoleController( Role role){
        roleService.insertRoleService(role);
    };
    @RequestMapping("updateRole")
    @ResponseBody
    public void UpdateRoleController( Role role){
        roleService.updateRoleService(role);
    };
    @RequestMapping("deleteRole")
    @ResponseBody
    public ResultInfo deleteRoleController(Integer id){
        roleService.deleteRoleService(id);
       return success("角色记录删除成功！");
    };
}
