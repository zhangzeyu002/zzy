package com.xxxx.crm.Query;

import com.xxxx.crm.base.BaseQuery;

public class RoleQuery extends BaseQuery {
    String roleName;

    public RoleQuery(String roleName) {
        this.roleName = roleName;
    }

    public RoleQuery() {
    }

    public String getUserName() {
        return roleName;
    }

    public void setUserName(String userName) {
        this.roleName = userName;
    }
}
