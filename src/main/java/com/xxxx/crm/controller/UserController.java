package com.xxxx.crm.controller;

import com.xxxx.crm.Query.UserQuery;
import com.xxxx.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectUserByArrays(UserQuery userQuery){
        return userService.queryUserByArrays(userQuery);

    }
    /**
     * 进⼊⽤户⻚⾯
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "user/index"; }
}
