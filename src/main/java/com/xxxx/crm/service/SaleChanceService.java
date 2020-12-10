package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.Query.SalechanceQuery;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.Sale_chanceMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.PhoneUtil;
import com.xxxx.crm.vo.Sale_chance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service

public class SaleChanceService extends BaseService<Sale_chance,Integer> {
    @Resource
    Sale_chanceMapper sale_chanceMapper;
    /*
    * 多条件查询Sale_chance
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String ,Object> queryByArrays(SalechanceQuery salechanceQuery) {
        Map<String, Object> map = new HashMap<>();
        /*
        * PageHelper函数放在查询语句的前面，会将我们查询的结果进行拦截
        * salechanceQuery.getPage()；指的是当前需要的是第几页
        * salechanceQuery.getLimit()：指的是每一页需要多少行数据
        * 拦截到的数据按照每页多少行进行分配，自动标配页数，我们可以根据第一个参数进行得到
        * 所想要的结果
        * */
        PageHelper.startPage(salechanceQuery.getPage(),salechanceQuery.getLimit());
        //或得我们通过拦截得到的结果及其其他的信息。
        PageInfo<Sale_chance> pageInfo=new PageInfo<Sale_chance>(sale_chanceMapper.selectByParams(salechanceQuery));
        map.put("code",200);
        map.put("mag","sueccss");
        map.put("count",pageInfo.getTotal());//pageInfo.getTotal()总条数
        map.put("data",pageInfo.getList());//pageInfo.getList()得到捕获的结果
        return map;
    }
    /**
     * 营销机会添加
     *  1.必填项
            customerName:非空
     *      linkMan:     非空
     *      linkPhone:   非空 11位手机号
     *  2.设置默认值
     *      isvalid    数据是否有效  1有效  0无效
     *      createDate   数据创建时间、当前系统时间
     *      updateDate   数据修改时间、当前系统时间
     *      createMan    添加数据的人员（在cookie中获取登录用户名称）交给controller直接设置进去
     *
     *      判断前台是否选择了分配人
     *         如果分配了人员
     *              assignTime分配时间   当前时间
     *              state分配状态        已分配  0未分配   1已分配
     *              devResult开发状态    开发中0-未开发 1-开发中 2-开发成功 3-开发失败
     *         如果未分配人员
     *              assignTime分配时间    null
     *              state分配状态        未分配   0未分配   1已分配
     *              devResult开发状态    未开发 0-未开发 1-开发中 2-开发成功 3-开发失败
     *
     *  3.添加数据，调用dao，判断是否添加成功
     * @return
     */
    public void insertSaleChance(Sale_chance sale_chance){
            //1.必填项
        checknoNUll(sale_chance.getCustomerName(),sale_chance.getLinkMan(),sale_chance.getLinkPhone());
            //2：设置默认值
        sale_chance.setIsValid(1);
        sale_chance.setCreateDate(new Date());
        sale_chance.setUpdateDate(new Date());
            if(sale_chance.getAssignMan()!=null){
                sale_chance.setAssignTime(null);
                sale_chance.setState(0);
                sale_chance.setDevResult(0);
            }else
            {
                sale_chance.setAssignTime(new Date());
                sale_chance.setState(1);
                sale_chance.setDevResult(1);
            }
    }

    private void checknoNUll(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName),"客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"电话不能为空");
        //验证手机号码格式问题
        AssertUtil.isTrue( !PhoneUtil.isMobile(linkPhone),"手机格式有误！");

    }
}
