package com.xxxx.crm.Query;

import com.xxxx.crm.base.BaseQuery;

public class SalechanceQuery extends BaseQuery {

    private String customerName; // 客户名称
    private String createMan; // 创建人
    private String state; // 分配状态

    public SalechanceQuery() {
    }

    public SalechanceQuery(String customerName, String createMan, String state) {
        this.customerName = customerName;
        this.createMan = createMan;
        this.state = state;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
