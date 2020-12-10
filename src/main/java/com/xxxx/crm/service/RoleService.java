package com.xxxx.crm.service;

import com.xxxx.crm.Query.RoleQuery;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.RoleMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class RoleService extends BaseService<Role,Integer> {
    @Resource
    RoleMapper roleMapper;
    //role查询
    public List<Map<String,Object>> selectRoleservice(){
       return roleMapper.selectRole();
    }
    public Role selectByRoleNameService(RoleQuery roleQuery){
        //1:判断参数非空
        AssertUtil.isTrue(StringUtils.isBlank(roleQuery.getUserName()),"输入参数名为空，请重新输入");
        //2：调用方法
        Role role = roleMapper.selectRoleByName(roleQuery);
        return role;
    }
    //role增加
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRoleService(Role role){
        System.out.println("role = " + role.getRoleName());
        //校验数据
        checkRoleData(role);
        //添加默认项
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.insertSelective(role)<1,"添加数据失败");
    }
    //role修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRoleService(Role role){
        //校验数据
        checkRoleData02(role);
        //添加默认项
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.insertSelective(role)<1,"添加数据失败");
    }

    private void checkRoleData02(Role role) {
        AssertUtil.isTrue(roleMapper.selectByPrimaryKey(role.getId()).getRoleName()==null,"选择角色失败");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"请输入你想成为的名称！");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective (role)<1,"更新数据失败");
    }

    private void checkRoleData(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"请输入你要添加角色的名字！");
        AssertUtil.isTrue(roleMapper.selectRoleByName02(role.getRoleName())!=null,"该角色已经存在");
    }

    //role删
    @Transactional(propagation = Propagation.REQUIRED)
        public   void deleteRoleService(Integer id){
        AssertUtil.isTrue(id==null,"请选中你要删除的角色对象");
        AssertUtil.isTrue( roleMapper.deleteByPrimaryKey(id)<1,"删除失败");
    }
}
