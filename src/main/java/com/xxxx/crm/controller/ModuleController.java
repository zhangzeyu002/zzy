package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.model.TreeModel;
import com.xxxx.crm.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
@RequestMapping("Module")
@Controller
public class ModuleController extends BaseController {
    @Resource
    ModuleService moduleService;
    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeModel> queryAllModules(){ return moduleService.queryAllModules(); }
}
