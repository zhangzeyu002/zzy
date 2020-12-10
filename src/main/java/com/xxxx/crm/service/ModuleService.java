package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.ModuleMapper;
import com.xxxx.crm.model.TreeModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Module;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {
    @Resource
    ModuleMapper moduleMapper;
    //查询Model所有信息
    @Transactional
    public List<TreeModel>  queryAllModules(){

       return moduleMapper.queryAllModules();
    }
    @Transactional
    public void insertModel(Module module){
        //数据校验
        AssertUtil.isTrue(module.getModuleName()==null,"添加用户名不能为空");

    }
}
