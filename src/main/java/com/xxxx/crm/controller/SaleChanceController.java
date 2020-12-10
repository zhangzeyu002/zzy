package com.xxxx.crm.controller;

import com.xxxx.crm.Query.SalechanceQuery;
import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.dao.Sale_chanceMapper;
import com.xxxx.crm.service.SaleChanceService;
import com.xxxx.crm.utils.CookieUtil;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.Sale_chance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
    @Resource
    SaleChanceService saleChanceService;
    @RequestMapping("queryArrays")
    @ResponseBody
    public Map<String ,Object> QueryArraysSalechance(SalechanceQuery salechanceQuery){
        Map<String, Object> stringObjectMap = saleChanceService.queryByArrays(salechanceQuery);
        return  stringObjectMap;}
/*** 进入营销机会页面 * @return */
@RequestMapping("select")
        public String select ()
    { return "saleChance/sale_chance"; }

    @RequestMapping("insertSale")
    @ResponseBody
    public  String insertSalechance(Sale_chance sale_chance, HttpServletRequest request){
        //通过cookie获取目前创建人的Id，从而获取创建人的姓名
        String userName = CookieUtil.getCookieValue(request, "userName");
        sale_chance.setCreateMan(userName);
        saleChanceService.insertSaleChance(sale_chance);
        return "添加成功！";
    }
    @RequestMapping("insert")
    public String insert ()
    { return "saleChance/add_update"; }
}
